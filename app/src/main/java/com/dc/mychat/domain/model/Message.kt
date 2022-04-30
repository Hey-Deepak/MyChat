package com.dc.mychat.domain.model

import com.google.firebase.Timestamp



data class Message (
    val message: String = "",
    val timestamp: Timestamp = Timestamp(1212122L, 121212),
    val senderMailId: String = ""
        )
