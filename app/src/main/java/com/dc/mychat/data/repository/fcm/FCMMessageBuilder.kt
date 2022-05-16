package com.dc.mychat.data.repository.fcm

import android.util.Base64
import android.util.Base64.DEFAULT
import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.NewMessageNotification
import com.dc.mychat.domain.repository.UserRepository
import com.google.firebase.Timestamp
import com.google.gson.Gson


object FCMMessageBuilder {

    fun buildNewMessageNotification(
        newMessageNotification: NewMessageNotification
    ): String {
        var msg = Gson().toJson(
            newMessageNotification
        )

        msg = String(
            Base64.encode(msg.toByteArray(), DEFAULT)
        )

        return """{
            "to": "/topics/users",
            "data": {
                "msg": "$msg"
            }
        }""".trimIndent()
    }


}