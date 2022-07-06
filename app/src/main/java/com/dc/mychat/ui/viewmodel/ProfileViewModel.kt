package com.dc.mychat.ui.viewmodel

import android.net.Uri
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val serverRepository: ServerRepository
): ViewModel() {

    val profileState = mutableStateOf<Profile?>(null)


    fun createProfile(profile: Profile, navHostController: NavHostController, sharedViewModel: SharedViewModel) {

        viewModelScope.launch {
            Log.d("TAG PVM", "Create Profile ${profileState.value} ")

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

            Log.d("TAG 9.5.2", "Inside SharedViewModel createProfile End")

            // Navigate to All Users Screen
            navHostController.navigate(Screen.AllUsers.route){
                popUpTo(Screen.Profile.route){ inclusive = true}
            }
        }
    }


    private fun saveProfileToPrefs(profile: Profile) {
        viewModelScope.launch {
            localRepository.saveProfileToPrefs(profile = profile)
            Log.d("TAG", "ProfileViewmodel, profile saved to prefs ${profile.toString()}")
        }
    }


    private fun saveIsProfileCreatedStatusToPrefs(statusOfProfile: Boolean) {
        viewModelScope.launch {
            localRepository.saveIsProfileCreatedStatusToPrefs(statusOfProfile)
            Log.d("TAG", "ProfileViewmodel, profileStatus saved to prefs ${statusOfProfile}")
        }
    }
}