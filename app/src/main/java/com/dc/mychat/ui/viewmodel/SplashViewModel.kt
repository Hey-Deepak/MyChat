package com.dc.mychat.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.dc.mychat.Screen
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.LocalRepository
import com.dc.mychat.domain.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val localRepository: LocalRepository
):ViewModel() {
    private val isProfileCreatedState = mutableStateOf(false)

    fun readProfileFromPrefsAndNavigate(navHostController: NavHostController) {
        viewModelScope.launch {
            isProfileCreatedState.value = localRepository.getIsProfileCreatedFromPrefs()
            if (isProfileCreatedState.value){
                navHostController.navigate(Screen.AllUsers.route){
                    popUpTo(Screen.Splash.route) {inclusive = true}
                }
            } else {
                navHostController.navigate(Screen.Login.route){
                    popUpTo(Screen.Splash.route) {inclusive = true}
                }
            }
        }
    }
}