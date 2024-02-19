package com.dc.mychat.ui.screens

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardChat(navHostController: NavHostController, userProfile: Profile, sharedViewModel: SharedViewModel) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
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
