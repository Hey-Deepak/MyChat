package com.dc.mychat.data.repository.remote

import android.util.Log
import androidx.core.net.toUri
import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.Messages
import com.dc.mychat.domain.repository.MessageRepository
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore


import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

import kotlinx.coroutines.isActive
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MessageRepositoryImp() : MessageRepository {

    private val firebaseChatCollectionRef = Firebase.firestore.collection("Chats")
    private val storageRef = Firebase.storage.reference


    override suspend fun sendMessage(message: Message, groupId: String) {

        if (message.imageUri.isNotEmpty()) {
            val imageUrl = uploadImageToFireStorage(message, groupId)
            message.imageUri = imageUrl
        }

        val doc = firebaseChatCollectionRef.document(groupId).get().await()
        if (doc.exists()) {
            firebaseChatCollectionRef.document(groupId)
                .update("messageArray", FieldValue.arrayUnion(message)).await()
        } else {
            firebaseChatCollectionRef.document(groupId)
                .set(Messages(listOf(message))).await()
        }

    }


    override suspend fun uploadImageToFireStorage(message: Message, groupId: String): String {
        storageRef.child("images/chats/${groupId}/${message.timestamp}")
            .putFile(message.imageUri.toUri()).await()
        val photoPath =
            storageRef.child("images/chats/${groupId}/${message.timestamp}").downloadUrl.await()
        return photoPath.toString()
    }


    override suspend fun getAllMessagesFromFirebase(groupId: String): MutableList<Message> {
        val listOfMessages = firebaseChatCollectionRef.document(groupId).get().await()
            .toObject(Messages::class.java)?.messageArray ?: emptyList()
        return listOfMessages.toMutableList()

    }


    override fun subscribeForMessages(groupId: String): List<Message> {

        var listOfMessages = listOf<Message>()
        Log.d("TAG 19.5", "GroupId. $groupId")
        firebaseChatCollectionRef.document(groupId).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.d("TAG 19.2", "Listen failed.", e)
                return@addSnapshotListener
            }
            Log.d("TAG 19.6", "Snapshot. ${snapshot?.data}")
            if (snapshot != null && snapshot.exists()) {
                listOfMessages =
                    snapshot.toObject(Messages::class.java)?.messageArray ?: emptyList()
                Log.d("TAG 19.3", "Current data: ${snapshot.data}")
            } else {
                Log.d("TAG 19.4", "Current data: null")
            }
        }

        Log.d("TAG 19.7", "Current data: ${listOfMessages}")
        return listOfMessages //Issue
    }

    override suspend fun subscribeToMessages3(
        groupId: String,
        onChanged: (Messages) -> Unit
    ) {
        firebaseChatCollectionRef.document(groupId).addSnapshotListener { documentSnapshot, e ->


            val messages = documentSnapshot?.toObject(Messages::class.java)

            if (messages == null) {
                //onError(e.toString())
            } else {
                onChanged(messages)
            }

        }

    }


    override suspend fun subcribeToMessages2(groupId: String): List<Message> {
        Log.d("TAG 19.10.1", "GroupId: $groupId")
        return suspendCoroutine { continuation ->
            firebaseChatCollectionRef.document(groupId).addSnapshotListener { docs, e ->

                val messages = docs?.toObject(Messages::class.java)
                Log.d("TAG 19.10.2", "Current data: $messages")
                Log.d("TAG 19.10.3", "Current data: ${continuation.context.isActive}")
                messages?.let { messages ->
                    if (continuation.context.isActive) {
                        Log.d("TAG 19.10.4", "Current data: ${messages.messageArray}")
                        continuation.resume(messages.messageArray)
                    } else {
                        messages.messageArray
                    }
                }
                if (messages == null) {
                    Log.d("TAG 19.10.5", "Message Null, first message to sent")
                    return@addSnapshotListener
                }

            }

        }

    }
}
