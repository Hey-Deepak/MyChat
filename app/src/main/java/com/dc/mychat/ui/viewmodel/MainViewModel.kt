package com.dc.mychat.ui.viewmodel

import android.provider.ContactsContract
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dc.mychat.R
import com.dc.mychat.model.Message
import com.dc.mychat.model.Profile
import com.dc.mychat.repository.MessageRepository
import com.dc.mychat.repository.ProfileRepository
import com.dc.mychat.repository.UserRepository
import com.dc.mychat.ui.viewmodel.state.MainUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val userRepository: UserRepository,
    val messageRepository: MessageRepository,
    val profileRepository: ProfileRepository
): ViewModel() {


    val uiState = mutableStateOf<MainUIState>(MainUIState.IsLoggedIn(false))

    init {
        Log.d("TAG", "Init block")
    }


    /*fun isLoggedIn(flag: Boolean) {
        Log.d("TAG", "Inside IsLoggedIn function")
        uiState.value = MainUIState.Profile(Profile("Deepak", "choudharydeepak@gmail.com", R.drawable.ic_add_profile_picture))
    }*/

    fun onLoggedInClicked(email: String){
        uiState.value = MainUIState.Profile(Profile("Deepak", "choudharydeepak@gmail.com", R.drawable.ic_add_profile_picture))
    }

}