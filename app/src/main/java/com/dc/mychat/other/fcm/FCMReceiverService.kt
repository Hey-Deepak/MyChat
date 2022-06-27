package com.dc.mychat.other.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Base64
import android.util.Base64.DEFAULT

import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.dc.mychat.MainActivity
import com.dc.mychat.R
import com.dc.mychat.domain.model.NewMessageNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import java.util.*

class FCMReceiverService : FirebaseMessagingService() {

    companion object {
        const val TAG = "FCMReceiverService"
        const val CHANNEL_ID = "notifications"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d("TAG 12.0", "FCM Notification ")
        //val type = remoteMessage.data["type"]?.toInt() ?: return
        val msgString = remoteMessage.data["msg"] ?: return
        Log.d("TAG 12.1", "msgString Notification $msgString")
        val msg = parseMsg<NewMessageNotification>(msgString)
        msg?.let {
            // TODO()
            showNotification(it)
        } ?: kotlin.run {
            Log.d("TAG FCM 12.2", "unable to parse message object of type : type")
        }


    }

    private fun showNotification(message: NewMessageNotification) {
        createNotificationChannel()

        //PendingIntent
        val intent = Intent(this, MainActivity::class.java)
        //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        //Build
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(message.senderMailId)
            .setContentText(message.message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message.message))
            .setSmallIcon(R.drawable.ic_chat_app_icon)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        //Post
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(Random().nextInt(30000) + 1000, builder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, "Default Notifications", importance)
            channel.description = "for default notifications"
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

}

private inline fun <reified T> parseMsg(msgString: String): T? {
    //Decode message
    val msg = Gson().fromJson(
        String(Base64.decode(msgString, DEFAULT)),
        T::class.java
    )

    return msg
}