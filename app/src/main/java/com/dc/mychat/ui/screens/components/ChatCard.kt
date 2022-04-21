package com.dc.mychat.ui.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dc.mychat.Screen
import com.dc.mychat.domain.model.Message
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.dc.mychat.ui.viewmodel.state.MainUIState

@ExperimentalMaterialApi
@Composable
fun CardChat(mainViewModel: MainViewModel, navHostController: NavHostController) {
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp),
        onClick = {
            onUserClicked(mainViewModel.messageRepository.getAllMessagesFromRepository(), mainViewModel, navHostController)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(100.dp),
                border = BorderStroke(1.dp, Color.Green),

                ) {
                AsyncImage(model = mainViewModel.profileState.value.displayPhoto,
                    contentDescription = "display photo")
            }
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = mainViewModel.profileState.value.displayName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    fontSize = 24.sp
                )
                Text(
                    text = mainViewModel.profileState.value.mailId,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Blue
                )

            }
        }

    }
}

fun onUserClicked(allMessages: List<Message>, mainViewModel: MainViewModel, navHostController: NavHostController) {
    mainViewModel.uiState.value = MainUIState.AllMessages(allMessages)
    navHostController.navigate(Screen.Message.route)
    Log.d("TAG1", "Inside onUserClicked ")
}
