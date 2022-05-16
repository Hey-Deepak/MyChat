package com.dc.mychat.ui.screens.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*


import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dc.mychat.domain.model.Message
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.google.firebase.Timestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun SendMessageCard(mainViewModel: MainViewModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 8.dp,
        border = BorderStroke(1.dp, color = Color.Blue)
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedTextField(
                value = mainViewModel.textState.value,
                onValueChange = {
                    mainViewModel.textState.value = it
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

                trailingIcon = {
                    IconButton(onClick = {
                            mainViewModel.sendMessage(
                                message = Message(
                                    mainViewModel.textState.value,
                                    Timestamp.now(),
                                    mainViewModel.senderMailIdState.value
                                )
                            )
                    }) {
                        Icon(imageVector = Icons.Filled.Send, contentDescription = "Send Message")
                    }
                },
                textStyle = TextStyle(fontSize = 20.sp),
                label = {
                    Text(text = "Type Message")
                }
            )
        }
    }
}
