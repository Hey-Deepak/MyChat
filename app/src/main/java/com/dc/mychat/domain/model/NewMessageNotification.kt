package com.dc.mychat.domain.model

data class NewMessageNotification(
    val message: String,
    val receiverMailId: String,
    val senderMailId: String,
)