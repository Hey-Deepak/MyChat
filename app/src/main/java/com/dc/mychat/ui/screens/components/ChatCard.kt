package com.dc.mychat.ui.screens

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dc.mychat.Screen
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.ui.viewmodel.AllUsersViewModel
import com.dc.mychat.ui.viewmodel.SharedViewModel

@ExperimentalMaterialApi
@Composable
fun CardChat(navHostController: NavHostController, userProfile: Profile, sharedViewModel: SharedViewModel) {
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp),
        onClick = {
            onUserClicked(navHostController, userProfile, sharedViewModel)
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
                AsyncImage(model = Uri.parse(userProfile.displayPhoto),
                    contentDescription = "display photo")
            }
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = userProfile.displayName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    fontSize = 24.sp
                )
                Text(
                    text = userProfile.mailId,
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
    profile: Profile,
    sharedViewModel: SharedViewModel
) {
    sharedViewModel.addReceiverProfile(profile)
    navHostController.navigate(Screen.Message.route)
}
