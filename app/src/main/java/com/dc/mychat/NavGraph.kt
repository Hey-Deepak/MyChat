package com.dc.mychat

import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

    LaunchedEffect(Unit) {
        mainViewModel.getStatus()
    }

    mainViewModel.loginStatusState.value?.let { loginStatus ->
        mainViewModel.profileStatusState.value?.let { profileStatus ->
            val startDestination =
                if (loginStatus) {
                    if (profileStatus) {
                        Screen.AllUsers.route
                    } else {
                        Screen.Profile.route
                    }
                } else {
                    Screen.Login.route
                }

            Log.d("TAG 2.0","Inside Nav Graph ${startDestination}")

            NavHost(navController = navHostController, startDestination = startDestination){
                composable(route = Screen.Login.route){
                    LoginScreen(mainViewModel = mainViewModel, navHostController = navHostController, launchLoginFlow)
                }
                composable(route = Screen.Profile.route){
                    ProfileScreen(mainViewModel = mainViewModel, navHostController = navHostController, selectImageLauncher)
                }
                composable(route = Screen.AllUsers.route){
                    AllUsersScreen(mainViewModel = mainViewModel, navHostController = navHostController)
                }
                composable(route = Screen.Message.route){
                    MessageScreen(mainViewModel = mainViewModel, navHostController)
                }
            }
        }
    }


}

