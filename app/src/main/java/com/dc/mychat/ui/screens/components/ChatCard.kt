package com.dc.mychat.ui.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dc.mychat.Screen
import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.ui.viewmodel.MainViewModel

@ExperimentalMaterialApi
@Composable
fun CardChat(mainViewModel: MainViewModel, navHostController: NavHostController, profile: Profile) {
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp),
        onClick = {
            onUserClicked(navHostController, mainViewModel, profile)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(100.dp),
                border = BorderStroke(1.dp, Color.Green),

                ) {
                AsyncImage(model = Uri.parse(profile.displayPhoto),
                    contentDescription = "display photo")
            }
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = profile.displayName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    fontSize = 24.sp
                )
                Text(
                    text = profile.mailId,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Blue
                )

            }
        }

    }
}

fun onUserClicked(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    profile: Profile
) {

    mainViewModel.receiverMailIdState.value = profile.mailId
    Log.d("TAG1", "Inside onUserClicked RC ${mainViewModel.receiverMailIdState.value}")
    mainViewModel.getMailIdFromSharedPrefs()
    mainViewModel.groupIdState.value = "${mainViewModel.senderMailIdState.value}%${mainViewModel.receiverMailIdState.value}"
    Log.d("TAG2", "Inside onUserClicked RC & Sender ${mainViewModel.groupIdState.value}")
    mainViewModel.allMessagesState.value.clear()
    mainViewModel.getAllMessageFromFirebase()
    navHostController.navigate(Screen.Message.route)
}
