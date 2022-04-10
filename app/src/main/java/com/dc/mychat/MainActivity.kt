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
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dc.mychat.ui.theme.MyChatTheme
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel: MainViewModel by viewModels()
    private lateinit var loginLauncher: ActivityResultLauncher<Intent>
    lateinit var navHostController: NavHostController

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLoginLauncher()
        setContent {
            MyChatTheme {
                navHostController = rememberNavController()
                SetupNavGraph(navHostController = navHostController, mainViewModel, loginLauncher)
            }
        }


    }


    private fun setupLoginLauncher() {
        Log.d("TAG","Inside setupLoginLauncher")
        loginLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            Log.d("TAG","Inside setupLoginLauncher")
            if (result.resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                user?.let {
                    val email = it.email!!
                    Log.d("TAG","Inside setupLoginLauncher $email")
                    mainViewModel.userRepository.saveEmailToPrefs(email)


                    Toast.makeText(
                        this,
                        "Congratulation! You have logged in as $email",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyChatTheme {
      //  MainScreen()
    }
}