package com.dc.mychat.other.fcm

import android.util.Base64
import android.util.Base64.DEFAULT
import com.dc.mychat.domain.model.NewMessageNotification
import com.google.gson.Gson


object FCMMessageBuilder {

    fun buildNewMessageNotification(
        newMessageNotification: NewMessageNotification
    ): String {
        val msg = Gson().toJson(
            newMessageNotification
        )

        return jsonToBase64String(msg)
    }


    fun jsonToBase64String(msg: String):String{
        val tempMsg = String(
            Base64.encode(msg.toByteArray(), DEFAULT)
        )
        return """{
            "to": "/topics/users",
            "data": {
                "msg": "$tempMsg"
            }
        }""".trimIndent()
    }
}

