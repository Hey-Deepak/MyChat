package com.dc.mychat.ui.screens

import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dc.mychat.Screen
import com.dc.mychat.ui.viewmodel.MainViewModel


@Composable
fun ProfileScreen(
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    selectImageLauncher: ActivityResultLauncher<String>
) {

    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AsyncImage(
            model = mainViewModel.imageUriState.value.toString(),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )


        OutlinedTextField(
            value = mainViewModel.profileState.value.displayName,
            onValueChange = { mainViewModel.profileState.value = mainViewModel.profileState.value.copy(
                displayName = it
            ) },
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
                createProfile(mainViewModel = mainViewModel, navHostController)
            },
            modifier = Modifier.padding(8.dp),
        ) {
            Text(text = "Done", fontSize = 16.sp)
        }
    }


}

fun createProfile(mainViewModel: MainViewModel, navHostController: NavHostController) {

    mainViewModel.serverRepository.createProfile(mainViewModel)
    mainViewModel.serverRepository.getAllProfile(mainViewModel = mainViewModel)
    navHostController.navigate(Screen.AllUsers.route)
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
