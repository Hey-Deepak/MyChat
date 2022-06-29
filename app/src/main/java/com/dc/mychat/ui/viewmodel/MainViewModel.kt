package com.dc.mychat.ui.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.dc.mychat.Screen
import com.dc.mychat.other.fcm.FCMMessageBuilder
import com.dc.mychat.other.fcm.FCMSender
import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.NewMessageNotification
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.MessageRepository
import com.dc.mychat.domain.repository.ServerRepository
import com.dc.mychat.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class MainViewModel @Inject constructor(
    val userRepository: UserRepository,
    val messageRepository: MessageRepository,
    val serverRepository: ServerRepository,
) : ViewModel() {


    val imageUriState = mutableStateOf<Uri?>(null)
    val profileState = mutableStateOf(Profile("", "", ""))
    val profileStatusState = mutableStateOf<Boolean?>(false)
    var allUsersState = mutableStateOf(listOf(profileState.value))
    var allMessagesState = mutableStateListOf<Message>()
    val receiverMailIdState = mutableStateOf("")
    val receiverNameState = mutableStateOf("")

    val senderMailIdState = mutableStateOf("${userRepository.getLoginEmailFromPrefs()}")
    val textState = mutableStateOf("")
    val groupIdState = mutableStateOf(
        listOf(senderMailIdState.value, receiverMailIdState.value).sorted().joinToString("%")
    )
    val loginStatusState = mutableStateOf<Boolean?>(false)


    fun getAllMessageFromFirebase() {
        viewModelScope.launch {
            messageRepository.subscribeToMessages3(groupIdState.value) {
                allMessagesState.clear()
                allMessagesState.addAll(it.messageArray)

            }
        }
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            allMessagesState.add(message)
            Log.d("TAG 9.7.1 MainViewModel", allMessagesState.toString())
            Log.d("TAG 9.7.2 MainViewModel", groupIdState.value)
            Log.d("TAG 9.7.3 MainViewModel", senderMailIdState.value)
            messageRepository.sendMessage(message = message, groupId = groupIdState.value)
            val data = FCMMessageBuilder.buildNewMessageNotification(
                NewMessageNotification(
                    message.message, receiverMailIdState.value, senderMailIdState.value
                )
            )
            suspendCoroutine<String> { continuation ->
                FCMSender().send(
                    data,
                    callback = object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            continuation.resumeWithException(e)
                        }

                        override fun onResponse(call: Call, response: Response) {
                            val responseMsg = response.body?.string() ?: ""
                            if (responseMsg.contains("message_id"))
                                continuation.resume(response.message)
                            else
                                continuation.resumeWithException(
                                    Exception(responseMsg)
                                )
                        }
                    }
                )
            }
            textState.value = ""
        }
    }

    private fun getMailIdFromSharedPrefs() {
        viewModelScope.launch {
            senderMailIdState.value = userRepository.getProfileFromPrefs().mailId
        }
    }

    fun createProfile(profile: Profile, navHostController: NavHostController) {
        viewModelScope.launch {
            Log.d("TAG MVM", "Mainviewmodel, Create Profile ${imageUriState.value} ")

            if (imageUriState.value.toString().contains("content://")) {
                val downloadedUrl =
                    serverRepository.uploadProfilePicture(imageUriState.value!!, profile)
                serverRepository.createProfile(profile.copy(displayPhoto = downloadedUrl))
            } else {
                serverRepository.createProfile(profile)
            }
            saveProfileToPrefs(profile)
            saveProfileStatusToPrefs(true)
            Log.d("TAG 9.5.2", "Inside MainViewModel createProfile End")
            navHostController.navigate(Screen.AllUsers.route){
                popUpTo(Screen.Profile.route){ inclusive = true}
            }
        }
    }

    fun setProfileFromPrefs() {
        viewModelScope.launch(Dispatchers.Main) {
            profileState.value = userRepository.getProfileFromPrefs()
            imageUriState.value = Uri.parse(profileState.value.displayPhoto)
        }
    }

    private suspend fun saveProfileStatusToPrefs(status: Boolean) {
        userRepository.saveProfileStatusToPrefs(status)
    }

    fun saveProfileToPrefs(profile: Profile) {
        viewModelScope.launch {
            userRepository.saveProfileToPrefs(profile = profile)
            Log.d("TAG 9.6", "Mainviewmodel, profile saved to prefs ${profile.toString()}")
        }
    }

    fun getAllProfileFromFirebase() {
        viewModelScope.launch {
            allUsersState.value = serverRepository.getAllProfile()
        }
    }

    fun getStatus() {
        viewModelScope.launch {
            loginStatusState.value = userRepository.getLoginStatusFromPrefs()
            profileStatusState.value = userRepository.getProfileStatusToPrefs()
        }
    }

    private fun saveLoginStatusToPrefs(loginStatus: Boolean) {
        viewModelScope.launch {
            userRepository.saveLoginStatusToPrefs(loginStatus = loginStatus)
        }
    }

    fun getFirebaseUser(it: FirebaseUser, navHostController: NavHostController) {
        viewModelScope.launch(Dispatchers.Main) {
            Log.d("TAG", "getFirebaseUser: A")
            var profile = async {
                Log.d("TAG", "getFirebaseUser: B")
                serverRepository.fetchProfile(it)
            }.await()
            Log.d("TAG", "getFirebaseUser: C")
            if (profile == null) {
                profile = Profile(it.displayName.toString(), it.email!!, it.photoUrl.toString())
            }

            imageUriState.value = Uri.parse(profile.displayPhoto)
            profileState.value = profile
            loginStatusState.value = true
            saveLoginStatusToPrefs(true)
            saveProfileToPrefs(profile)
            Log.d("TAG 9.6", "loginStatus set to true")
            navHostController.navigate(Screen.Profile.route){
                popUpTo(Screen.Login.route){ inclusive = true }
            }
        }
    }

    fun onUserClicked(profile: Profile) {
        receiverMailIdState.value = profile.mailId
        receiverNameState.value = profile.displayName
        Log.d(
            "TAG 9.1",
            "Inside onUserClicked RC mainviewModel ${receiverMailIdState.value} & profile = ${profile.mailId}"
        )
        getMailIdFromSharedPrefs()
        groupIdState.value =
            listOf(senderMailIdState.value, receiverMailIdState.value).sorted().joinToString("%")
        Log.d("TAG 9.2", "Inside onUserClicked RC & Sender mainviewModel ${groupIdState.value}")
        allMessagesState.clear()
        getAllMessageFromFirebase()
    }
}