package com.dc.mychat.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
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
import com.dc.mychat.ui.screens.components.*
import com.dc.mychat.ui.viewmodel.AllUsersViewModel
import com.dc.mychat.ui.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AllUsersScreen(
    allUsersViewModel: AllUsersViewModel,
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        allUsersViewModel.getAllProfileFromFirebase()
        Log.d("TAG", "AllUsersScreen: getAllProfileFromFirebase")

    }
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            var bottomNavItems = listOf(
                BottomNavItem(
                    name = "Home",
                    route = "home",
                    icon = Icons.Rounded.Home,
                ),
                BottomNavItem(
                    name = "Create",
                    route = "add",
                    icon = Icons.Rounded.AddCircle,
                ),
                BottomNavItem(
                    name = "Settings",
                    route = "settings",
                    icon = Icons.Rounded.Settings,
                ),
            )

            Scaffold(
                topBar = {
                    TopBarForNavigation(
                        title = "MyChat",
                        buttonIcon = painterResource(id = R.drawable.profile),
                        onNavBarClicked = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        },
                        onProfileButtonClicked = {
                            navHostController.navigate(Screen.Profile.route)
                        }
                    )
                },
                scaffoldState = scaffoldState,
                drawerContent = {
                    Text(text = "Profile")
                    DrawerContent(items = bottomNavItems, navHostController = navHostController)
                }

            ) {
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




        }
        LoadingDialog(isDialogShowing = allUsersViewModel.loadingState.value)
        ErrorDialog(
            allUsersViewModel.showErrorState.value,
            allUsersViewModel.showErrorMessageState.value
        ) {
            allUsersViewModel.showErrorState.value = it
        }
    }
}



