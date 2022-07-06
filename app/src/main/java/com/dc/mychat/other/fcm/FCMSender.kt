package com.dc.mychat.other.fcm

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class FCMSender {

    companion object {
        private const val FCM_URL = "https://fcm.googleapis.com/fcm/send"
        private const val KEY_STRING =
            "key=AAAAOLn-xf0:APA91bHNSUhzyjbNL331tmzQq13aaFC908_fWn3tx2LRGTYcSGXHsJF4F9uN_TPVbuUIUACNrEnMJ5QOKETLn5otmvpEnKPA2W_2DdVL3TSuyG3IRfOidtn7m5CCmzku35q-n-ruuyFJ"
    }

    private var client = OkHttpClient()

    fun send(payload: String, callback: Callback) {
        client.newCall(
            Request.Builder()
                .url(FCM_URL)
                .method("POST", payload.toRequestBody())
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", KEY_STRING)
                .build()
        ).enqueue(callback)
    }
}