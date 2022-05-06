package com.dc.mychat.ui.screens


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dc.mychat.ui.screens.components.MessageCard
import com.dc.mychat.ui.screens.components.SendMessageCard
import com.dc.mychat.ui.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MessageScreen(mainViewModel: MainViewModel) {
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {

        Box(modifier = Modifier.weight(10f)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                state = listState
            ) {

                items(items = mainViewModel.allMessagesState) { message ->

                    MessageCard(message = message, mainViewModel = mainViewModel)
                    Log.d("TAG 14", message.toString())
                }
                CoroutineScope(Dispatchers.Main).launch {
                    if (mainViewModel.allMessagesState.isNotEmpty()) {
                        listState.scrollToItem(mainViewModel.allMessagesState.size - 1)
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), contentAlignment = Alignment.Center
        ) {
            SendMessageCard(mainViewModel)
        }

    }
}

