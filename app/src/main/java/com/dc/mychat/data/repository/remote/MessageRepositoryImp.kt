package com.dc.mychat.data.repository.remote

import android.util.Log
import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.Messages
import com.dc.mychat.domain.repository.MessageRepository
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MessageRepositoryImp() : MessageRepository {

    val firebaseChatCollectionRef = Firebase.firestore.collection("Chats")


    override suspend fun sendMessage(message: Message, groupId: String) {
        Log.d("TAG 19.1 Message & GroupId Message Repo ", "$message + $groupId")
        val doc = firebaseChatCollectionRef.document(groupId).get().await()
        if (doc.exists()) {
            firebaseChatCollectionRef.document(groupId)
                .update("messageArray", FieldValue.arrayUnion(message)).await()
        } else {
            firebaseChatCollectionRef.document(groupId)
                .set(Messages(listOf(message))).await()
        }


    }


    /*override suspend fun getAllMessagesFromFirebase(): List<Message> {
        val groupName = "gljat999@gmail.com%choudharydeepak990@gmail.com"
        return suspendCoroutine { cont ->
            firebaseChatCollectionRef.document(groupName).get().addOnSuccessListener {

                val messages = it.toObject(Messages::class.java)
                cont.resume(messages!!.messageArray)
            }.addOnFailureListener {
                cont.resumeWithException(it)
            }
        }
    }*/

    override suspend fun getAllMessagesFromFirebase(groupId: String): MutableList<Message> {
        Log.d("TAG 19.9", "${groupId}")
        var listOfMessages = listOf<Message>()

        listOfMessages = firebaseChatCollectionRef.document(groupId).get().await()
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
