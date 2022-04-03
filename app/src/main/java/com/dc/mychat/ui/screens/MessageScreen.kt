package com.dc.mychat.ui.screens


import android.util.Log
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
fun MessageScreen( mainViewModel: MainViewModel) {
    Log.d("TAG", "Inside Message Screen2")
    val messages: List<Message> = mainViewModel.messageRepository.getAllMessagesFromRepository()
    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.SpaceBetween) {
        Log.d("TAG", "Inside Message Screen3")
        Column(modifier = Modifier,
        verticalArrangement = Arrangement.Top) {
            Log.d("TAG", "Inside Message Screen4")
            for (message in messages){
                Log.d("TAG", "Inside for loop")
                MessageCard(message = message, mainViewModel)
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
