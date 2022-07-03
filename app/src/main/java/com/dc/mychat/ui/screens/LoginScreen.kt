package com.dc.mychat.ui.screens


import android.util.Log
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
import com.dc.mychat.ui.viewmodel.LoginViewModel
import com.dc.mychat.ui.viewmodel.SharedViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navHostController: NavHostController,
    launchLoginFlow: (() -> Unit) -> Unit,
    sharedViewModel: SharedViewModel
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
            Log.d("TAG1","Inside Button")
            launchLoginFlow {
                val user = FirebaseAuth.getInstance().currentUser
                user?.let {
                    Log.d("TAG 2","Inside Button LoggedIn Screen ${it.toString()}")
                    loginViewModel.getFirebaseUser(it, navHostController,sharedViewModel)
                    Log.d("TAG 3","Inside Button LoggedIn Screen ${it.toString()}")

                }
            }

        }) {
            Text(text = "Login",
                fontSize = 20.sp)
        }

    }

}

