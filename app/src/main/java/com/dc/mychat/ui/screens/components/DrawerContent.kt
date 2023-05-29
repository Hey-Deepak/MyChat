package com.dc.mychat.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.dc.mychat.R
import com.dc.mychat.Screen
import com.dc.mychat.ui.screens.BottomNavItem

@Composable
fun DrawerContent(
    items: List<BottomNavItem>,
    navHostController: NavHostController
) {
    Column(modifier = Modifier.fillMaxSize().background(color = Color.Blue)) {
        items.forEach {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,


                ) {
                IconButton(onClick = { navHostController.navigate(Screen.Profile.route) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile"
                    )
                }
            }
        }
    }
}