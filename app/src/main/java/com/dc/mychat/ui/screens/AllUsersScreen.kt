package com.dc.mychat.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dc.mychat.ui.viewmodel.AllUsersViewModel
import com.dc.mychat.ui.viewmodel.SharedViewModel

@ExperimentalMaterialApi
@Composable
fun AllUsersScreen(
    allUsersViewModel: AllUsersViewModel,
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    allUsersViewModel.getAllProfileFromFirebase()
    Log.d("TAG", "All User Screen, SENDER = ${sharedViewModel.senderProfile} RECEIVER ${sharedViewModel.receiverProfile} ")
    Surface() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

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
                LazyColumn() {
                    items(items = allUsersViewModel.allUsersState.value) { userProfile ->
                        CardChat(navHostController, userProfile, sharedViewModel)
                    }
                }
            }
        }
    }
}



