package com.dc.mychat.ui.screens


import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dc.mychat.R
import com.dc.mychat.Screen
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoggedInScreen(
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    launchLoginFlow: (() -> Unit) -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_chat_app_icon),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(0.6f))

        Text(text = "Welcome to My Chat",
            modifier = Modifier.padding(16.dp),
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )

        Button(onClick = {
            Log.d("TAG","Inside Button")
            launchLoginFlow {
                val user = FirebaseAuth.getInstance().currentUser
                user?.let {
                    mainViewModel.getFirebaseUser(it)
                    navHostController.navigate(Screen.Profile.route)
                }
            }

        }) {
            Text(text = "Login",
                fontSize = 20.sp)
        }

    }
}

fun fireLoginIntent(loginLauncher: ActivityResultLauncher<Intent>) {
    val intent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(listOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        ))
        .build()
    loginLauncher.launch(intent)
}
