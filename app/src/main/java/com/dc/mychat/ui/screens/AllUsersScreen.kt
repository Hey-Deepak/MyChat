package com.dc.mychat.ui.screens

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dc.mychat.R
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.ui.screens.components.TopBar
import com.dc.mychat.ui.viewmodel.MainViewModel

@ExperimentalMaterialApi
@Composable
fun AllUsersScreen(mainViewModel: MainViewModel, navHostController: NavHostController) {
    mainViewModel.getAllProfileFromFirebase()
    Log.d("TAG 13.1", "All User Screen, All profile got from firebase ")
    Surface() {
        Column(modifier = Modifier
            .fillMaxSize()) {

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
                    LazyColumn(){
                        items(items = mainViewModel.allUsersState.value){ profile ->
                            CardChat(mainViewModel, navHostController, profile)
                        }
                    }
            }
        }
    }
}



