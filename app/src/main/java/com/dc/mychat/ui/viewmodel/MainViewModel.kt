package com.dc.mychat.ui.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.MessageRepository
import com.dc.mychat.domain.repository.ServerRepository
import com.dc.mychat.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
            getAllProfileFromFirebase()
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
            senderMailIdState.value = userRepository.getLoggedInEmailFromPrefs().toString()
        }
    }

    fun createProfile(profile: Profile) {
        viewModelScope.launch {
            serverRepository.createProfile(profile)
        }
    }

    fun saveProfileToPrefs(profile: Profile) {
        viewModelScope.launch {
            userRepository.saveProfileToPrefs(profile = profile)
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
        }
    }

    fun saveLoginStatusToPrefs(loginStatus: Boolean) {
        viewModelScope.launch {
            userRepository.saveLoginStatusToPrefs(loginStatus = loginStatus)
        }
    }

    fun getFirebaseUser(it: FirebaseUser) {
        val email = it.email!!
        val displayPhoto = it.photoUrl.toString()
        val displayName = it.displayName.toString()
        val profile = Profile(displayName, email, displayPhoto)

        Log.d("TAG", "Inside launchLoginFlow $email, $displayName, $displayPhoto")

        imageUriState.value = Uri.parse(displayPhoto)
        profileState.value = profile

        saveProfileToPrefs(profile)
        saveLoginStatusToPrefs(true)
    }

    fun onUserClicked(profile: Profile) {
        receiverMailIdState.value = profile.mailId
        Log.d("TAG4", "Inside onUserClicked RC mainviewModel ${receiverMailIdState.value}")
        getMailIdFromSharedPrefs()
        groupIdState.value =
            listOf(senderMailIdState.value, receiverMailIdState.value).sorted().joinToString("%")
        Log.d("TAG5", "Inside onUserClicked RC & Sender mainviewModel ${groupIdState.value}")
        allMessagesState.clear()
        getAllMessageFromFirebase()
    }
}