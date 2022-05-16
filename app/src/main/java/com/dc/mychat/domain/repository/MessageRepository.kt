package com.dc.mychat.domain.repository

import android.util.Log
import com.dc.mychat.R
import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.Messages
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.ui.viewmodel.MainViewModel

interface MessageRepository {

    suspend fun sendMessage(message: Message, groupId: String)

    suspend fun getAllMessagesFromFirebase(groupId: String): MutableList<Message>

    fun subscribeForMessages(groupId: String): List<Message>

    suspend fun subcribeToMessages2(groupId: String): List<Message>

    suspend fun subscribeToMessages3(
        groupId: String,
        onChanged: (Messages) -> Unit
    )

}