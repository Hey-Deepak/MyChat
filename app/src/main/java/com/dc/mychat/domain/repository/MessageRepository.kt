package com.dc.mychat.domain.repository

import android.util.Log
import com.dc.mychat.R
import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.ui.viewmodel.MainViewModel

interface MessageRepository {


    fun getProfileFromRepository(): Profile 

    fun sendMessage(message: Message)

    suspend fun getAllMessagesFromFirebase(mainViewModel: MainViewModel): List<Message>
}