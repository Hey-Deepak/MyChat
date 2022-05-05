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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
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
    val allMessagesState = mutableStateOf<List<Message>>(listOf())
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
            textState.value = ""
        }
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            messageRepository.sendMessage(message = message, groupId = groupIdState.value)
        }
    }

    fun getMailIdFromSharedPrefs() {
        viewModelScope.launch {
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
        viewModelScope.launch {
            loginStatusState.value = userRepository.getLoginStatusFromPrefs()
        }
    }

    fun saveLoginStatusToPrefs(loginStatus: Boolean){
        viewModelScope.launch {
            userRepository.saveLoginStatusToPrefs(loginStatus = loginStatus)
        }
    }
}