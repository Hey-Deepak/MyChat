package com.dc.mychat.ui.viewmodel

import android.net.Uri
import android.util.Log
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
    val profileState = mutableStateOf<Profile>(Profile("", "", ""))
    var allUsersState = mutableStateOf<List<Profile>>(listOf(profileState.value))
    val allMessagesState = mutableStateOf<MutableList<Message>>(mutableListOf())
    val receiverMailIdState = mutableStateOf("")
    val senderMailIdState = mutableStateOf("${userRepository.getLoggedInEmailFromPrefs()}")
    val textState = mutableStateOf("")
    val messageState = mutableStateOf(Message())
    val groupIdState = mutableStateOf(senderMailIdState.value + "%" + receiverMailIdState.value)
    val loginStatusState = mutableStateOf(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllProfileFromFirebase()
        }

    }


    fun getAllMessageFromFirebase() {
        Log.d("TAG 18", groupIdState.value)
        viewModelScope.launch {
            allMessagesState.value = messageRepository.getAllMessagesFromFirebase(groupIdState.value)
        }
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            //allMessagesState.value.add(message)
            Log.d("TAG 9 MainViewModel", allMessagesState.value.toString())
            messageRepository.sendMessage(message = message, groupId = groupIdState.value)
            textState.value = ""
            getAllMessageFromFirebase()
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

    fun getAllProfileFromFirebase(){
        viewModelScope.launch {
            allUsersState.value = serverRepository.getAllProfile()
        }
    }

    fun getLoginStatus(){
        runBlocking {
            loginStatusState.value = userRepository.getLoginStatusFromPrefs()
        }
    }

    fun saveLoginStatusToPrefs(loginStatus: Boolean){
        viewModelScope.launch {
            userRepository.saveLoginStatusToPrefs(loginStatus = loginStatus)
        }
    }

    fun getFirebaseUser(it: FirebaseUser){
        val email = it.email!!
        val displayPhoto = it.photoUrl.toString()
        val displayName = it.displayName.toString()
        val profile = Profile(displayName, email, displayPhoto)

        Log.d("TAG","Inside launchLoginFlow $email, $displayName, $displayPhoto")

        imageUriState.value = Uri.parse(displayPhoto)
        profileState.value = profile

        saveProfileToPrefs(profile)
        saveLoginStatusToPrefs(true)
    }
}