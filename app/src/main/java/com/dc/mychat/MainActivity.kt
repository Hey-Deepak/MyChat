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
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dc.mychat.ui.theme.MyChatTheme
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.firebase.ui.auth.AuthUI
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel: MainViewModel by viewModels()
    lateinit var navHostController: NavHostController

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerLoginLauncher()
        setContent {
            MyChatTheme {
                navHostController = rememberNavController()
                SetupNavGraph(navHostController = navHostController, mainViewModel, ::launchLoginFlow, selectImageLauncher)
            }
        }


    }

    val selectImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            Log.d("TAG4", "Inside selectImageLauncher")
            mainViewModel.imageUriState.value = uri
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

