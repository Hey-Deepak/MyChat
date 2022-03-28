package com.dc.mychat.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
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

@Composable
fun AllUsersScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "All Users",
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp),

        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            elevation = 8.dp,

            ) {
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.ic_add_profile_picture),
                    contentDescription = "User Profile Picture",
                    Modifier.padding(8.dp)
                )
                Column() {
                    Text(
                        text = "Deepak Choudhary",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(text = "choudharydeepak990@gmail.com",
                        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Light)
                }
            }
        }
    }
}


@Preview
@Composable
fun viewAllUsersScreen() {
    AllUsersScreen()
}