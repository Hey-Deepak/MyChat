package com.dc.mychat.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dc.mychat.R
import com.dc.mychat.ui.viewmodel.MainViewModel

@Composable
fun LoggedInScreen(emailId: String, mainViewModel: MainViewModel) {
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

        Button(onClick = { mainViewModel.isLoggedIn(true) }) {
            Text(text = "Login",
                fontSize = 20.sp)
        }

    }
}


/*
@Preview
@Composable
fun viewNotLoggedIn() {
    LoggedInScreen("choudharydeepak@gmail.com")
}*/
