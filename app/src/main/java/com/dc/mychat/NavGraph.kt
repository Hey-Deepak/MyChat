package com.dc.mychat

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dc.mychat.domain.repository.UserRepository
import com.dc.mychat.ui.screens.*
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.dc.mychat.ui.viewmodel.state.MainUIState

@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(navController: NavHostController, mainViewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Screen.LoggedIn.route){
        composable(route = Screen.LoggedIn.route){
            MainScreen(mainViewModel = mainViewModel)
        }
        composable(route = Screen.Profile.route){
            val profile = (mainViewModel.uiState.value as MainUIState.Profile).profile
            ProfileScreen(profile = profile, mainViewModel = mainViewModel)
        }
        composable(route = Screen.AllUsers.route){
            val profiles = (mainViewModel.uiState.value as MainUIState.AllUsers).listOfUsers
            AllUsersScreen(profiles = profiles, mainViewModel = mainViewModel)
        }
        composable(route = Screen.Message.route){
            MessageScreen(mainViewModel = mainViewModel)
        }
    }
}