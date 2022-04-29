package com.dc.mychat.data.repository.remote

import android.util.Log
import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.MessageRepository
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MessageRepositoryImp() : MessageRepository {

    val firebaseDatabaseRef = Firebase.firestore
    val firebaseChatCollectionRef = Firebase.firestore.collection("Chats")
    private val listOfMessages = mutableListOf<Message>()

    override fun getProfileFromRepository(): Profile {
        TODO("Not yet implemented")
    }

    override fun sendMessage(message: Message) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllMessagesFromFirebase(mainViewModel: MainViewModel): List<Message> {
        val groupName = "gljat999@gmail.com%choudharydeepak990@gmail.com"
        firebaseChatCollectionRef.document(groupName).get().addOnSuccessListener {
            val data = it.data
            Log.d("TAG 25", "$data")
            val message = Message(data?.get("message").toString(),
                data?.get("timestamp").toString(),
                data?.get("senderMailId").toString())
            listOfMessages.add(message)
        }

        mainViewModel.allMessagesState.value = listOfMessages


        return listOfMessages
    }
}