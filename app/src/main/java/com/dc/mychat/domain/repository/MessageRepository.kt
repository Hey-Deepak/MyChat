package com.dc.mychat.domain.repository

import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.Messages

interface MessageRepository {

    suspend fun sendMessage(message: Message, groupId: String)

    suspend fun getAllMessagesFromFirebase(groupId: String): MutableList<Message>

    fun subscribeForMessages(groupId: String): List<Message>

    suspend fun subcribeToMessages2(groupId: String): List<Message>

    suspend fun subscribeToMessages3(
        groupId: String,
        onChanged: (Messages) -> Unit
    )

    suspend fun uploadImageToFireStorage(message: Message, groupId: String): String

}