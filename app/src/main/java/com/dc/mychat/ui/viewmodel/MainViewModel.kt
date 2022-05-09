package com.dc.mychat.ui.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.MessageRepository
import com.dc.mychat.domain.repository.ServerRepository
import com.dc.mychat.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val userRepository: UserRepository,
    val messageRepository: MessageRepository,
    val serverRepository: ServerRepository
) : ViewModel() {

    val imageUriState = mutableStateOf<Uri?>(null)
    val profileState = mutableStateOf(Profile("", "", ""))
    var allUsersState = mutableStateOf(listOf(profileState.value))
    val allMessagesState = mutableStateListOf<Message>()
    val receiverMailIdState = mutableStateOf("")
    val senderMailIdState = mutableStateOf("${userRepository.getLoggedInEmailFromPrefs()}")
    val textState = mutableStateOf("")
    val groupIdState = mutableStateOf(
        listOf(senderMailIdState.value, receiverMailIdState.value).sorted().joinToString("%")
    )
    val loginStatusState = mutableStateOf(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {

        }

    }

    fun getAllMessageFromFirebase() {
        Log.d("TAG 18", groupIdState.value)
        viewModelScope.launch {
            allMessagesState.addAll(messageRepository.getAllMessagesFromFirebase(groupIdState.value))
        }
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            allMessagesState.add(message)
            Log.d("TAG 9 MainViewModel", allMessagesState.toString())
            Log.d("TAG 10 MainViewModel", groupIdState.value)
            messageRepository.sendMessage(message = message, groupId = groupIdState.value)
            textState.value = ""
        }
    }

    fun getMailIdFromSharedPrefs() {
        runBlocking {
            senderMailIdState.value = userRepository.getProfileFromPrefs().mailId
        }
    }

    fun createProfile(profile: Profile) {
        viewModelScope.launch {
            Log.d("TAG 9.5", "Mainviewmodel, Create Profile ")
            serverRepository.createProfile(profile)
            saveLoginStatusToPrefs(true)
            Log.d("TAG 9.5.2", "Inside MainViewModel createProfile End")
        }
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

    fun getLoginStatus() {
        runBlocking {
            loginStatusState.value = userRepository.getLoginStatusFromPrefs()
            Log.d("TAG 9.3", loginStatusState.toString())
        }
    }

    fun saveLoginStatusToPrefs(loginStatus: Boolean) {
        viewModelScope.launch {
            userRepository.saveLoginStatusToPrefs(loginStatus = loginStatus)
        }
    }

    fun getFirebaseUser(it: FirebaseUser) {
            runBlocking {
                val email = it.email!!
                val displayPhoto = it.photoUrl.toString()
                val displayName = it.displayName.toString()
                val profile = Profile(displayName, email, displayPhoto)

                Log.d("TAG 9.4.1", "Inside MainViewModel getFirebaseUser $email, $displayName, $displayPhoto")

                imageUriState.value = Uri.parse(displayPhoto)
                profileState.value = profile
                loginStatusState.value = true

                saveProfileToPrefs(profile)
                getLoginStatus()
                Log.d("TAG 9.6","loginStatus set to true")

            }

    }

    fun onUserClicked(profile: Profile) {
        receiverMailIdState.value = profile.mailId
        Log.d("TAG 9.1", "Inside onUserClicked RC mainviewModel ${receiverMailIdState.value} & profile = ${profile.mailId}")
        getMailIdFromSharedPrefs()
        groupIdState.value =
            listOf(senderMailIdState.value, receiverMailIdState.value).sorted().joinToString("%")
        Log.d("TAG 9.2", "Inside onUserClicked RC & Sender mainviewModel ${groupIdState.value}")
        allMessagesState.clear()
        getAllMessageFromFirebase()
    }
}