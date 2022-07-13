package com.dc.mychat.ui.screens

import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dc.mychat.R
import com.dc.mychat.ui.screens.components.ErrorDialog
import com.dc.mychat.ui.screens.components.LoadingDialog
import com.dc.mychat.ui.viewmodel.ProfileViewModel
import com.dc.mychat.ui.viewmodel.SharedViewModel


@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    navHostController: NavHostController,
    selectImageLauncher: ActivityResultLauncher<String>,
    sharedViewModel: SharedViewModel
) {

    // Set Profile State
    profileViewModel.profileState.value = sharedViewModel.senderProfile

    Log.d("TAG", "Inside profile screen start ${profileViewModel.profileState.value}")
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(250.dp),
            contentAlignment = Alignment.BottomEnd
        ) {


            AsyncImage(
                model = profileViewModel.profileState.value!!.displayPhoto,
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )

            Box(
                modifier = Modifier.size(54.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.ic_add_image_2),
                    contentDescription = "Add Image",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            selectImageLauncher.launch("image/*")
                        }
                )
            }
        }

        OutlinedTextField(
            value = profileViewModel.profileState.value!!.displayName,
            onValueChange = {
                profileViewModel.profileState.value = profileViewModel.profileState.value!!.copy(
                    displayName = it
                )
            },
            label = { Text("Enter Your Name", fontSize = 16.sp) },
            maxLines = 1,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.SemiBold, fontSize = 24.sp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

            )

        Button(
            onClick = {
                createProfile(profileViewModel, navHostController, sharedViewModel)
            },
            modifier = Modifier.padding(8.dp),
        ) {
            Text(text = "Done", fontSize = 16.sp)
        }
        LoadingDialog(isDialogShowing = profileViewModel.loadingState.value)
        ErrorDialog(isDialogShowing = profileViewModel.showErrorState.value,
            errorMessage = profileViewModel.showErrorMessageState.value){
            profileViewModel.showErrorState.value = it
        }
    }

}

fun createProfile(profileViewModel: ProfileViewModel, navHostController: NavHostController, sharedViewModel: SharedViewModel) {
    Log.d("TAG", "createProfile: ${profileViewModel.profileState.value}")
    profileViewModel.createProfile(profileViewModel.profileState.value!!, navHostController, sharedViewModel)

}


