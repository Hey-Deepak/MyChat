package com.dc.mychat.ui.screens.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.dc.mychat.R

@Composable
fun TopBar(title: String, buttonIcon: Painter, onButtonClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() } ) {
                Icon(buttonIcon, contentDescription = "")
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}

@Composable
fun TopBarForNavigation(title: String, buttonIcon: Painter, onNavBarClicked: () -> Unit, onProfileButtonClicked:()-> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        backgroundColor = MaterialTheme.colors.primaryVariant,
        navigationIcon = {
                         IconButton(onClick = { onNavBarClicked() }) {
                             Icon(painter = painterResource(id = R.drawable.ic_camera), contentDescription = "Menu" )
                         }
        },
        actions = {
            IconButton(onClick = { onProfileButtonClicked() }) {
                Icon(buttonIcon, contentDescription = "profile")
            }
        }
    )

}