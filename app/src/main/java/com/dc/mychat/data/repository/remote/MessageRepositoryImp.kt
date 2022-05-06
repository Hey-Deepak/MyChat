package com.dc.mychat.data.repository.remote

import android.util.Log
import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.Messages
import com.dc.mychat.domain.repository.MessageRepository
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class MessageRepositoryImp() : MessageRepository {

    val firebaseChatCollectionRef = Firebase.firestore.collection("Chats")


    override suspend fun sendMessage(message: Message, groupId: String) {
        Log.d("TAG 19 Message & GroupId Message Repo ", "$message + $groupId")
        val doc = firebaseChatCollectionRef.document(groupId).get().await()
        if(doc.exists()) {
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
        Log.d("TAG 18", "${groupId}")
        var listOfMessages = listOf<Message>()

        listOfMessages = firebaseChatCollectionRef.document(groupId).get().await()
            .toObject(Messages::class.java)?.messageArray ?: emptyList()
        return listOfMessages.toMutableList()

    }
}