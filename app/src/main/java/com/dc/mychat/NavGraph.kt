package com.dc.mychat

import androidx.activity.result.ActivityResultLauncher
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dc.mychat.ui.screens.*
import com.dc.mychat.ui.viewmodel.MainViewModel

@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    launchLoginFlow: (() -> Unit) -> Unit,
    selectImageLauncher: ActivityResultLauncher<String>
) {
    NavHost(navController = navHostController, startDestination = Screen.LoggedIn.route){
        composable(route = Screen.LoggedIn.route){
            LoggedInScreen(mainViewModel = mainViewModel, navHostController = navHostController, launchLoginFlow)
        }
        composable(route = Screen.Profile.route){
            ProfileScreen(mainViewModel = mainViewModel, navHostController = navHostController, selectImageLauncher)
        }
        composable(route = Screen.AllUsers.route){
            AllUsersScreen(mainViewModel = mainViewModel, navHostController = navHostController)
        }
        composable(route = Screen.Message.route){
            MessageScreen(mainViewModel = mainViewModel)
        }
    }
}