package com.dc.mychat.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dc.mychat.MainActivity
import com.dc.mychat.R
import com.dc.mychat.Screen
import com.dc.mychat.ui.screens.components.ErrorDialog
import com.dc.mychat.ui.screens.components.LoadingDialog
import com.dc.mychat.ui.screens.components.TopBar
import com.dc.mychat.ui.screens.components.TopBarForNavigation
import com.dc.mychat.ui.viewmodel.AllUsersViewModel
import com.dc.mychat.ui.viewmodel.SharedViewModel

@ExperimentalMaterialApi
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

            TopBarForNavigation(title = "MyChat", buttonIcon = painterResource(id = R.drawable.profile)) {
                navHostController.navigate(Screen.Profile.route)
            }

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {

                // TODO : Need to add home screen icon

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



