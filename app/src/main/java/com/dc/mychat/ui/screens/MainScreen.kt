package com.dc.mychat.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import com.dc.mychat.model.Message
import com.dc.mychat.model.Profile
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.dc.mychat.ui.viewmodel.state.MainUIState

@Composable
fun MainScreen(mainViewModel: MainViewModel) {

    Log.d("TAG", "Inside Main Screen")
    when (mainViewModel.uiState.value) {
        is MainUIState.LoggedIn -> {
            Log.d("TAG", "Inside LoggedIn Screen")
            LoggedInScreen("choudharydeepak@gmail.com", mainViewModel)
            mainViewModel.uiState.value = MainUIState.IsLoggedIn(true)
        }
        is MainUIState.AllUsers -> {
            val profiles =
                ((mainViewModel.uiState.value as MainUIState.AllUsers).listOfUsers) as List<Profile>
            AllUsersScreen(profiles = profiles)
        }
        is MainUIState.Profile -> {
            val profile = (mainViewModel.uiState.value as MainUIState.Profile).profile
            ProfileScreen(profile)
        }

        is MainUIState.NewMessage ->
            MessageScreen(messages = ((mainViewModel.uiState.value as MainUIState.NewMessage).listOfMessage as List<Message>), mainViewModel)

        is MainUIState.IsLoggedIn -> {

            Log.d("TAG", "Profile Screen")
            LoggedInScreen("choudharydeepak@gmail.com", mainViewModel)
        }
    }
}