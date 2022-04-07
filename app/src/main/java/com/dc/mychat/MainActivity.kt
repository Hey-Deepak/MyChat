package com.dc.mychat

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dc.mychat.ui.screens.MainScreen
import com.dc.mychat.ui.theme.MyChatTheme
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.dc.mychat.ui.viewmodel.state.MainUIState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel: MainViewModel by viewModels()
    lateinit var navController: NavHostController

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyChatTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController, mainViewModel)
            }
        }

        setUpLoginScreen()
    }

    private fun setUpLoginScreen() {
       /* mainViewModel.uiState.value = MainUIState.LoggedIn("choudharydeepak@gmail.com")
        mainViewModel.userRepository.saveEmailToPrefs("choudharydeepak@gmail.com")*/
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyChatTheme {
      //  MainScreen()
    }
}