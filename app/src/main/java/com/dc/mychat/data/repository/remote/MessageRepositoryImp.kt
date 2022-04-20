package com.dc.mychat.data.repository.remote

import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.MessageRepository

class MessageRepositoryImp() : MessageRepository {
    override fun getAllMessagesFromRepository(): List<Message> {
        TODO("Not yet implemented")
    }

    override fun getProfileFromRepository(): Profile {
        TODO("Not yet implemented")
    }

    override fun sendMessage(message: Message) {
        TODO("Not yet implemented")
    }

    override fun getAllMessages(): List<Message> {
        TODO("Not yet implemented")
    }
}