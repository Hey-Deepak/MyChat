package com.dc.mychat.ui.screens


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.Top
        ) {

            items(items = mainViewModel.allMessagesState.value){ message ->
                MessageCard(message = message)
                Log.d("TAG 14", message.toString())
            }

        }
        SendMessageCard(mainViewModel)
    }

}

