package com.dc.mychat

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dc.mychat.ui.theme.MyChatTheme
import com.dc.mychat.ui.viewmodel.*
import com.firebase.ui.auth.AuthUI

import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val sharedViewModel: SharedViewModel by viewModels()
    val loginViewModel: LoginViewModel by viewModels()
    val profileViewModel: ProfileViewModel by viewModels()
    val allUsersViewModel: AllUsersViewModel by viewModels()
    val messagesViewModel : MessagesViewModel by viewModels()
    val splashViewModel : SplashViewModel by viewModels()

    lateinit var navHostController: NavHostController

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerLoginLauncher()
        FirebaseMessaging.getInstance().subscribeToTopic("users")
        setContent {
            MyChatTheme {
                navHostController = rememberNavController()
                SetupNavGraph(
                    navHostController = navHostController,
                    splashViewModel,
                    ::launchLoginFlow,
                    selectImageLauncher
                )
            }
        }


    }



    private val selectImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            Log.d("TAG MainActivity", "uri: $uri")
            if (uri != null){
                profileViewModel.profileState.value = profileViewModel.profileState.value!!.copy(displayPhoto = uri.toString())
                sharedViewModel.addSenderProfile(profileViewModel.profileState.value!!)
            }
        }



    private lateinit var loginLauncher: ActivityResultLauncher<Intent>

    // Step 3: Handler (to get the result)
    private lateinit var loginHandler: (() -> Unit)

    // Step 1: Registration
    private fun registerLoginLauncher() {
        Log.d("TAG","Inside setupLoginLauncher")
        loginLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            Log.d("TAG","Inside ActivityResult $result")
            if (result.resultCode == Activity.RESULT_OK) {
                Log.d("TAG","Inside ResultLambda ")
                loginHandler()
            }
        }
    }

    // Step 2: Launcher
    private fun launchLoginFlow(loginHandler: (() -> Unit)) {
        this.loginHandler = loginHandler

        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(listOf(
                AuthUI.IdpConfig.GoogleBuilder().build()
            ))
            .build()

        loginLauncher.launch(intent)
    }

}

