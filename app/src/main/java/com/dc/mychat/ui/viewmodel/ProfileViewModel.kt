package com.dc.mychat.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.dc.mychat.Screen
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.ServerRepository
import com.dc.mychat.domain.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val serverRepository: ServerRepository
): BaseViewModel() {

    val profileState = mutableStateOf<Profile?>(null)
    private val profileExceptionHandler = exceptionHandler


    fun createProfile(profile: Profile, navHostController: NavHostController, sharedViewModel: SharedViewModel) {
        viewModelScope.launch(profileExceptionHandler) {
            showToast("Profile is Creating")
            loadingState.value = true
            if (profileState.value!!.displayPhoto.contains("content://")) {
                val downloadedUrl =
                    serverRepository.uploadProfilePicture(profile)
                serverRepository.createProfile(profile.copy(displayPhoto = downloadedUrl))
            } else {
                serverRepository.createProfile(profile)
            }
            // Save Profile to Prefs
            sharedViewModel.addSenderProfile(profile)
            saveProfileToPrefs(profile)
            saveIsProfileCreatedStatusToPrefs(true)

            loadingState.value = false
            showToast("Profile is Created")

            // Navigate to All Users Screen
            navHostController.navigate(Screen.AllUsers.route){
                popUpTo(Screen.Profile.route){ inclusive = true}
            }
        }
    }


    private fun saveProfileToPrefs(profile: Profile) {
        viewModelScope.launch(profileExceptionHandler) {
            localRepository.saveProfileToPrefs(profile = profile)
            showToast("Profile Saved to Prefs")
        }
    }


    private fun saveIsProfileCreatedStatusToPrefs(statusOfProfile: Boolean) {
        viewModelScope.launch (profileExceptionHandler){
            localRepository.saveIsProfileCreatedStatusToPrefs(statusOfProfile)
            Log.d("TAG", "ProfileViewmodel, profileStatus saved to prefs ${statusOfProfile}")
        }
    }
}