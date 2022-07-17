package com.dc.mychat.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.NewMessageNotification
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.MessageRepository
import com.dc.mychat.other.fcm.FCMMessageBuilder
import com.dc.mychat.other.fcm.FCMSender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.system.measureTimeMillis

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val messageRepository: MessageRepository
) : BaseViewModel() {

    val allMessagesState = mutableStateListOf<Message>()
    val textState = mutableStateOf("")
    private val groupIdState = mutableStateOf("")
    private val messagesExceptionHandler = exceptionHandler


    fun getAllMessageFromFirebase(receiverProfile: Profile?, senderProfile: Profile?) {
        viewModelScope.launch(messagesExceptionHandler) {
            loadingState.value = true
            groupIdState.value =
                listOf(receiverProfile!!.mailId, senderProfile!!.mailId).sorted().joinToString("%")
            messageRepository.subscribeToMessages3(groupIdState.value) {
                allMessagesState.clear()
                allMessagesState.addAll(it.messageArray)
            }
            loadingState.value = false
        }
    }

    fun sendMessage(message: Message, sharedViewModel: SharedViewModel) {

        viewModelScope.launch(exceptionHandler) {
            loadingState.value = true
            allMessagesState.add(message)
            messageRepository.sendMessage(message = message, groupId = groupIdState.value)
            val data = FCMMessageBuilder.buildNewMessageNotification(
                NewMessageNotification(
                    message.message,
                    sharedViewModel.receiverProfile!!.mailId,
                    sharedViewModel.senderProfile!!.mailId
                )
            )
            suspendCoroutine<String> { continuation ->
                FCMSender().send(
                    data,
                    callback = object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            continuation.resumeWithException(e)
                        }

                        override fun onResponse(call: Call, response: Response) {
                            val responseMsg = response.body?.string() ?: ""
                            if (responseMsg.contains("message_id"))
                                continuation.resume(response.message)
                            else
                                continuation.resumeWithException(
                                    Exception(responseMsg)
                                )
                        }
                    }
                )
            }
            textState.value = ""
            loadingState.value = false
            Log.d("TAG", "sendMessage: loginState ${loadingState.value}")


        }

    }

}