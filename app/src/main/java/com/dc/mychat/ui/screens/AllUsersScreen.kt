package com.dc.mychat.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dc.mychat.ui.screens.components.ErrorDialog
import com.dc.mychat.ui.screens.components.LoadingDialog
import com.dc.mychat.ui.viewmodel.AllUsersViewModel
import com.dc.mychat.ui.viewmodel.SharedViewModel

@Composable
fun AllUsersScreen(
    allUsersViewModel: AllUsersViewModel,
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = Unit){
    allUsersViewModel.getAllProfileFromFirebase()
        Log.d("TAG", "AllUsersScreen: getAllProfileFromFirebase")

    }
    Surface {
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
        LoadingDialog(isDialogShowing = allUsersViewModel.loadingState.value)
        ErrorDialog(
            allUsersViewModel.showErrorState.value,
            allUsersViewModel.showErrorMessageState.value){
                allUsersViewModel.showErrorState.value = it
            }
    }
}



