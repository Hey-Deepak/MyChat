package com.dc.mychat.ui.screens

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.dc.mychat.model.Message
import com.dc.mychat.model.Profile
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.dc.mychat.ui.viewmodel.state.MainUIState

@ExperimentalMaterialApi
@Composable
fun MainScreen(mainViewModel: MainViewModel) {

    Log.d("TAG", "Inside Main Screen")
    when (mainViewModel.uiState.value) {
        is MainUIState.LoggedIn -> {
            Log.d("TAG", "Inside LoggedIn Screen")
            LoggedInScreen("choudharydeepak@gmail.com", mainViewModel)
        }
        is MainUIState.AllUsers -> {
            Log.d("TAG", "Inside AllUser Screen")
            val profiles =
                ((mainViewModel.uiState.value as MainUIState.AllUsers).listOfUsers) as List<Profile>
            AllUsersScreen(profiles = profiles, mainViewModel)
        }
        is MainUIState.Profile -> {
            Log.d("TAG", "Inside Profile Screen")
            val profile = (mainViewModel.uiState.value as MainUIState.Profile).profile
            ProfileScreen(profile, mainViewModel)
        }

        is MainUIState.NewMessage -> {
            Log.d("TAG", "Inside Message Screen")
            MessageScreen(
                mainViewModel
            )
        }
        is MainUIState.IsLoggedIn -> {
            Log.d("TAG", "LoggedInScreen")
            LoggedInScreen("choudharydeepak@gmail.com", mainViewModel)
        }
    }
}