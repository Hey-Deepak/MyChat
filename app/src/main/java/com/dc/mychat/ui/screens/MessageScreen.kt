package com.dc.mychat.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dc.mychat.model.Message
import com.dc.mychat.repository.MessageRepository
import com.dc.mychat.ui.screens.components.MessageCard
import com.dc.mychat.ui.screens.components.SendMessageCard
import com.dc.mychat.ui.viewmodel.MainViewModel

@Composable
fun MessageScreen(messages: List<Message>, mainViewModel: MainViewModel) {

    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.SpaceBetween) {

        Column(modifier = Modifier,
        verticalArrangement = Arrangement.Top) {
            for (message in messages){
                MessageCard(message = message)
            }
        }
        SendMessageCard(mainViewModel)
    }
}

/*
@Preview
@Composable
fun MessageScreenPreview() {
    val messages = MessageRepository().getAllMessagesFromrRepository()
    MessageScreen(messages = messages)
}*/
