package com.dc.mychat.repository

import com.dc.mychat.model.Message

class MessageRepository {
    fun getAllMessages(): List<Message>{
        return listOf(
            Message("Hi, My name is Deepak","12:01 AM", "choudharydeepak990@gmail.com"),
            Message("Hi, My name is Deepak2","12:02 AM", "gljat990@gmail.com"),
            Message("How are you?","12:03 AM", "choudharydeepak990@gmail.com"),
            Message("I am good bro, Hope you are also doing good in your domain.","12:04 AM", "gljat999@gmail.com"),
            Message("Yeah, I am good too. Now a days, I am learing about new things which may help me in future.","12:05 AM", "choudharydeepak990@gmail.com"),
            Message("Sounds, cool. Best wishes from my side","12:05 AM", "gljat999@gmail.com"),
        )
    }
}