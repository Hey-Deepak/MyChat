package com.dc.mychat.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dc.mychat.model.Message
import com.dc.mychat.repository.MessageRepository
import com.dc.mychat.ui.screens.components.MessageCard

@Composable
fun MessageScreen(messages: List<Message>) {
    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Top) {

        for (message in messages){
            MessageCard(message = message)
        }
    }
}

@Preview
@Composable
fun MessageScreenPreview() {
    val messages = MessageRepository().getAllMessages()
    MessageScreen(messages = messages)
}