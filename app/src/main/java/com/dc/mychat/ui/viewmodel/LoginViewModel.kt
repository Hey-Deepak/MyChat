package com.dc.mychat.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.dc.mychat.Screen
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.LocalRepository
import com.dc.mychat.domain.repository.ServerRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val serverRepository: ServerRepository
) : BaseViewModel() {


    private fun saveLoginStatusToPrefs(loginStatus: Boolean) {
        viewModelScope.launch(exceptionHandler) {
            localRepository.saveLoginStatusToPrefs(loginStatus = loginStatus)
        }
    }


    fun getFirebaseUser(it: FirebaseUser, navHostController: NavHostController, sharedViewModel: SharedViewModel) {
        viewModelScope.launch (exceptionHandler){
            loadingState.value = true
            Log.d("TAG", "Profile Fetching...")
            var profile = serverRepository.fetchProfile(it)
            if (profile == null) {
                profile = Profile(it.displayName.toString(), it.email!!, it.photoUrl.toString())
            }

            sharedViewModel.addSenderProfile(profile)
            saveLoginStatusToPrefs(true)
            saveProfileToPrefs(profile)
            saveIsProfileCreatedStatusToPrefs(true)
            Log.d("TAG", "loginStatus & Profile is set to prefs")

            loadingState.value = false

            navHostController.navigate(route = Screen.Profile.route){
                popUpTo(Screen.Login.route){ inclusive = true }
            }
    }
}

    private fun saveProfileToPrefs(profile: Profile) {
        viewModelScope.launch(exceptionHandler) {
            localRepository.saveProfileToPrefs(profile = profile)
            Log.d("TAG", "ProfileViewmodel, profile saved to prefs ${profile.toString()}")
        }
    }


    private fun saveIsProfileCreatedStatusToPrefs(statusOfProfile: Boolean) {
        viewModelScope.launch (exceptionHandler){
            localRepository.saveIsProfileCreatedStatusToPrefs(statusOfProfile)
            Log.d("TAG", "ProfileViewmodel, profileStatus saved to prefs ${statusOfProfile}")
        }
    }
}