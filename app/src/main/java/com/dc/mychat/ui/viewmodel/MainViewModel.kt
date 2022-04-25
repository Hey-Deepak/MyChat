package com.dc.mychat.ui.viewmodel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.dc.mychat.R
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.MessageRepository
import com.dc.mychat.domain.repository.ProfileRepository
import com.dc.mychat.domain.repository.ServerRepository
import com.dc.mychat.domain.repository.UserRepository
import com.dc.mychat.ui.viewmodel.state.MainUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val userRepository: UserRepository,
    val messageRepository: MessageRepository,
    val profileRepository: ProfileRepository,
    val serverRepository: ServerRepository
): ViewModel() {


    val uiState = mutableStateOf<MainUIState>(MainUIState.IsLoggedIn(false))
    val imageUriState = mutableStateOf<Uri?>(null)
    val profileState = mutableStateOf<Profile>(Profile("", "", ""))
    var allUsersState = mutableStateListOf<Profile>()





    fun fetchProfileFromServer(serverRepository: ServerRepository){
        serverRepository.getProfile(profileState.value.displayName)
    }

    fun fetchProfilesfromServer(serverRepository: ServerRepository){
        //Log.d("TAG 15", serverRepository.getAllProfile().toString())
       // allUsersState = serverRepository.getAllProfile()
    }

init {
   // fetchProfilesfromServer(serverRepository)
}

}