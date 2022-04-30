package com.dc.mychat.ui.screens


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dc.mychat.ui.screens.components.MessageCard
import com.dc.mychat.ui.screens.components.SendMessageCard
import com.dc.mychat.ui.viewmodel.MainViewModel

@Composable
fun MessageScreen(mainViewModel: MainViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Top
        ) {
            mainViewModel.allMessagesState.value.forEach {
                MessageCard(it)
            }
            SendMessageCard(mainViewModel)
        }
    }

}

