package com.dc.mychat

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.github.dhaval2404.imagepicker.ImagePicker

import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val sharedViewModel: SharedViewModel by viewModels()
    val profileViewModel: ProfileViewModel by viewModels()
    val splashViewModel : SplashViewModel by viewModels()

    lateinit var navHostController: NavHostController

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerLoginLauncher()
        registerImagePickerLauncher()
        FirebaseMessaging.getInstance().subscribeToTopic("users")
        setContent {
            MyChatTheme {
                navHostController = rememberNavController()
                SetupNavGraph(
                    navHostController = navHostController,
                    splashViewModel,
                    ::launchLoginFlow,
                    ::lauchImagePickerFlow,
                    profileViewModel
                )
            }
        }


    }



    private lateinit var loginLauncher: ActivityResultLauncher<Intent>
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>

    // Step 1: Registration
    private fun registerLoginLauncher() {
        Log.d("TAG","Inside setupLoginLauncher")
        loginLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            Log.d("TAG","Inside ActivityResult $result")
            if (result.resultCode == Activity.RESULT_OK) {
                Log.d("TAG","Inside ResultLambda ")
                loginHandler()
            } else Toast.makeText(this, "Not able to Login, Try Again", Toast.LENGTH_SHORT).show()
        }
    }
    private fun registerImagePickerLauncher() {
        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val uri = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    if (uri != null){
                        Log.d("TAG", "registerImagePickerLauncher: ${uri.data}")
                        profileViewModel.profileState.value = profileViewModel.profileState.value!!.copy(displayPhoto = uri.data.toString())
                        Log.d("TAG", "registerImagePickerLauncher: ${profileViewModel.profileState.value}")
                        sharedViewModel.addSenderProfile(profileViewModel.profileState.value!!)
                    } else Toast.makeText(this, "Not able to Pick Image", Toast.LENGTH_SHORT).show()
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(this, ImagePicker.getError(uri), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
                }
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
    private fun lauchImagePickerFlow(){
        Log.d("TAG", "lauchImagePickerFlow: launchImagePickerFlow")
        ImagePicker.with(this)
            .cropSquare()
            .compress(1024)
            .createIntent { intent ->
                imagePickerLauncher.launch(intent)
            }
    }

    // Step 3: Handler (to get the result)
    private lateinit var loginHandler: (() -> Unit)

}

