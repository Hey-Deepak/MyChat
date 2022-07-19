package com.dc.mychat.ui.screens.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import com.dc.mychat.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dc.mychat.domain.model.Message
import com.dc.mychat.ui.viewmodel.MessagesViewModel
import com.dc.mychat.ui.viewmodel.SharedViewModel
import com.google.firebase.Timestamp


@Composable
fun SendMessageCard(
    messagesViewModel: MessagesViewModel,
    sharedViewModel: SharedViewModel,
    launchImagePickerForMessageFlow: (() -> Unit) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedTextField(
                value = messagesViewModel.textState.value,
                onValueChange = {
                    messagesViewModel.textState.value = it
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

                trailingIcon = {
                    Row(modifier = Modifier.wrapContentSize()) {

                        // Send Message with Image Button
                        IconButton(onClick = {

                            launchImagePickerForMessageFlow() {
                                messagesViewModel.sendMessage(
                                    message = Message(
                                        messagesViewModel.textState.value,
                                        messagesViewModel.imageUriState.value,
                                        timestamp = Timestamp.now(),
                                        senderMailId = sharedViewModel.senderProfile!!.mailId
                                    ),
                                    sharedViewModel = sharedViewModel
                                )
                            }

                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = "Send Image"
                            )
                        }

                        // Send Message Button
                        IconButton(onClick = {
                            messagesViewModel.sendMessage(
                                message = Message(
                                    messagesViewModel.textState.value,
                                    messagesViewModel.imageUriState.value,
                                    timestamp = Timestamp.now(),
                                    senderMailId = sharedViewModel.senderProfile!!.mailId
                                ),
                                sharedViewModel = sharedViewModel
                            )
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Send,
                                contentDescription = "Send Message"
                            )
                        }
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
