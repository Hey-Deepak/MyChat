package com.dc.mychat.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
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
import com.dc.mychat.model.Profile
import com.dc.mychat.repository.ProfileRepository

@Composable
fun AllUsersScreen(profiles: List<Profile>) {
    Surface() {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "All Users",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
                for (profile in profiles){
                    CardChat(profile = profile)
                }
            }
        }
    }
}


@Preview
@Composable
fun viewAllUsersScreen() {
    val profiles = ProfileRepository().getAllProfiles()
   AllUsersScreen(profiles = profiles)
}