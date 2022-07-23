package com.dc.mychat.ui.screens


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dc.mychat.R
import com.dc.mychat.domain.model.Message
import com.dc.mychat.ui.screens.components.*
import com.dc.mychat.ui.viewmodel.MessagesViewModel
import com.dc.mychat.ui.viewmodel.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.Duration.Companion.days

@Composable
fun MessageScreen(
    messagesViewModel: MessagesViewModel,
    navHostController: NavController,
    sharedViewModel: SharedViewModel,
    launchImagePickerForMessageFlow: (() -> Unit) -> Unit
) {
    val listState = rememberLazyListState()

    // Set State of Message Screen
    val receiverProfile = sharedViewModel.receiverProfile
    val senderProfile = sharedViewModel.senderProfile
    Log.d("TAG", "MessageScreen: RECEIVER = $receiverProfile  SENDER = $senderProfile")

    // Get All Messages from Firebase
    LaunchedEffect(key1 = messagesViewModel.allMessagesState) {

        messagesViewModel.getAllMessageFromFirebase(receiverProfile, senderProfile)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {

        TopBar(
            title = receiverProfile!!.displayName,
            buttonIcon = painterResource(id = R.drawable.ic_back_arrow_back_24)
        ) {
            navHostController.popBackStack()
        }

        Box(modifier = Modifier.weight(10f)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                state = listState
            ) {

                for (i in messagesViewModel.allMessagesState.indices) {
                    val message = messagesViewModel.allMessagesState[i]
                    if (
                        i + 1 <= messagesViewModel.allMessagesState.size - 1 &&
                        messagesViewModel.allMessagesState[i].timestamp.toDate().date !=
                        messagesViewModel.allMessagesState[i + 1].timestamp.toDate().date) {
                        item {
                            MessageCard(message = message, sharedViewModel = sharedViewModel)
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = SimpleDateFormat(
                                        "dd MMMM",
                                        Locale.US
                                    ).format(messagesViewModel.allMessagesState[i+1].timestamp.toDate()),
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    } else {
                        item {
                            MessageCard(message = message, sharedViewModel = sharedViewModel)
                        }
                    }
                }

                CoroutineScope(Dispatchers.Main).launch {
                    if (messagesViewModel.allMessagesState.isNotEmpty()) {

                        listState.scrollToItem(messagesViewModel.allMessagesState.size - 1)
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), contentAlignment = Alignment.Center
        ) {
            SendMessageCard(messagesViewModel, sharedViewModel, launchImagePickerForMessageFlow)
        }

        LoadingDialog(isDialogShowing = messagesViewModel.loadingState.value)
        ErrorDialog(
            isDialogShowing = messagesViewModel.showErrorState.value,
            errorMessage = messagesViewModel.showErrorMessageState.value
        ) {
            messagesViewModel.showErrorState.value = it
        }
    }
}


