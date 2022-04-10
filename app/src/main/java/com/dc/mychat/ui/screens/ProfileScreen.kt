package com.dc.mychat.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dc.mychat.R
import com.dc.mychat.Screen
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.dc.mychat.ui.viewmodel.state.MainUIState

@Composable
fun ProfileScreen(mainViewModel: MainViewModel, navHostController: NavHostController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_add_profile_picture),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp, 200.dp)
                .clickable {
                    Toast
                        .makeText(
                            context,
                            "Profile Picture Clicked!",
                            Toast.LENGTH_LONG
                        )
                        .show()
                }
        )
        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter Your Name", fontSize = 20.sp) },
            maxLines = 1,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.SemiBold),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

            )

        Button(
            onClick = {
                createProfile(mainViewModel, navHostController)
            },
            modifier = Modifier.padding(8.dp),
        ) {
            Text(text = "Done", fontSize = 16.sp)
        }
    }


}

fun createProfile(mainViewModel: MainViewModel, navHostController: NavHostController) {

    navHostController.navigate(Screen.AllUsers.route)
}

fun onProfilePictureClicked() {
    TODO("Not yet implemented")


}

/*
@Preview
@Composable
fun viewProfileScreen() {
    ProfileScreen(
        profile = Profile(
            "Deepak",
            "choudharydeepak@gmail.com",
            R.drawable.ic_add_profile_picture
        )
    )
}*/
