package com.dc.mychat

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dc.mychat.ui.screens.MainScreen
import com.dc.mychat.ui.theme.MyChatTheme
import com.dc.mychat.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyChatTheme {
                // A surface container using the 'background' color from the theme
                Log.d("TAG", "Main Screen")
                MainScreen(mainViewModel)
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