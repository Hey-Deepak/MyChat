package com.dc.mychat.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.ui.viewmodel.MainViewModel

@ExperimentalMaterialApi
@Composable
fun AllUsersScreen(profiles: List<Profile>, mainViewModel: MainViewModel) {
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
                    CardChat(profile = profile, mainViewModel)
                }
            }
        }
    }
}


/*
@ExperimentalMaterialApi
@Preview
@Composable
fun viewAllUsersScreen() {
    val profiles = ProfileRepository().getAllProfiles()
   AllUsersScreen(profiles = profiles)
}*/
