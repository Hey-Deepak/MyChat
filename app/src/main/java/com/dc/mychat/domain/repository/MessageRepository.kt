package com.dc.mychat.domain.repository

import android.util.Log
import com.dc.mychat.R
import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.Profile

interface MessageRepository {


    fun getAllMessagesFromRepository(): List<Message>

    fun getProfileFromRepository(): Profile 

    fun sendMessage(message: Message)

    fun getAllMessages(): List<Message>
}