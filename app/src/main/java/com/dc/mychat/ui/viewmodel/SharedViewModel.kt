package com.dc.mychat.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dc.mychat.domain.model.Profile
import androidx.lifecycle.viewModelScope
import com.dc.mychat.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel() {

    var senderProfile by mutableStateOf<Profile?>(null)
    var receiverProfile by mutableStateOf<Profile?>(null)
    private val sharedExceptionHandler = exceptionHandler

    init {
        viewModelScope.launch(sharedExceptionHandler) {
            if (localRepository.getIsProfileCreatedFromPrefs())
            senderProfile = localRepository.getProfileFromPrefs()
        }
    }

    fun addSenderProfile(profile: Profile) {
        viewModelScope.launch {
            senderProfile = profile
        }
    }

    fun addReceiverProfile(profile: Profile) {
        viewModelScope.launch {
            receiverProfile = profile
        }
    }
}