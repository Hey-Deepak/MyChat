package com.dc.mychat

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dc.mychat.ui.screens.*
import com.dc.mychat.ui.screens.components.SplashScreen
import com.dc.mychat.ui.viewmodel.*

@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
    splashViewModel: SplashViewModel,
    launchLoginFlow: (() -> Unit) -> Unit,
    launchImagePickerFlow: () -> Unit,
    profileViewModel: ProfileViewModel
) {

    val sharedViewModel: SharedViewModel = viewModel()

    LaunchedEffect(Unit) {
        splashViewModel.readProfileFromPrefsAndNavigate(navHostController)
    }

    val startDestination = Screen.Splash.route
    Log.d("TAG", "SetupNavGraph: $startDestination")

    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(route = Screen.Splash.route) {
            SplashScreen()
        }
        composable(route = Screen.Login.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                loginViewModel = loginViewModel,
                navHostController = navHostController,
                launchLoginFlow,
                sharedViewModel
            )
        }
        composable(
            route = Screen.Profile.route
        ) {
            ProfileScreen(
                profileViewModel = profileViewModel,
                navHostController = navHostController,
                launchImagePickerFlow,
                sharedViewModel
            )
        }

        composable(route = Screen.AllUsers.route) {
            val allUsersViewModel: AllUsersViewModel = hiltViewModel()
            AllUsersScreen(
                allUsersViewModel = allUsersViewModel,
                navHostController = navHostController,
                sharedViewModel = sharedViewModel
            )
        }

        composable(
            route = Screen.Message.route
        ) {
            val messagesViewModel: MessagesViewModel = hiltViewModel()
            MessageScreen(messagesViewModel, navHostController, sharedViewModel)

        }
    }
}




