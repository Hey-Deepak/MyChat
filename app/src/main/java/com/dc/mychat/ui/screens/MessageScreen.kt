package com.dc.mychat.ui.screens


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dc.mychat.domain.model.Message
import com.dc.mychat.ui.screens.components.MessageCard
import com.dc.mychat.ui.screens.components.SendMessageCard
import com.dc.mychat.ui.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun MessageScreen( mainViewModel: MainViewModel) {
    Log.d("TAG", "Inside Message Screen2")

    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.SpaceBetween) {
        Log.d("TAG", "Inside Message Screen3")
        Column(modifier = Modifier,
        verticalArrangement = Arrangement.Top) {
            GlobalScope.launch(Dispatchers.Main) {
                val messages: List<Message> = mainViewModel.getAllMessagesFromFirebase()
                Log.d("TAG 6", "${messages}")
                Log.d("TAG", "Inside Message Screen4")
            for (message in messages){
                Log.d("TAG", "Inside for loop")
                MessageCard(message = message, mainViewModel)
            }
            }
        }
        SendMessageCard(mainViewModel)
    }
}

