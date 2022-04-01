package com.dc.mychat.ui.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*


import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dc.mychat.model.SendMessage


@Composable
fun SendMessageCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        elevation = 8.dp,
        border = BorderStroke(1.dp, color = Color.Blue)
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            var text by remember {
                mutableStateOf("Hello")
            }
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

                trailingIcon = {
                    IconButton(onClick = { sendMessage() }) {
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

fun sendMessage() {
    TODO("Not yet implemented")
}

@Preview
@Composable
fun SendMessageCardPreview() {

}