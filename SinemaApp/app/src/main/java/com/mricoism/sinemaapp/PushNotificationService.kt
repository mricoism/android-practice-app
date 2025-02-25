package com.mricoism.sinemaapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("onNewToken", token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("onMessageReceived A", "${message.messageId} | ${message.notification?.title} | ${message.notification?.body}")
        val title: String = message.notification?.title.toString()
        val body: String = message.notification?.body.toString()
        Log.d("onMessageReceived B", "$title | $body")

        // Ensure Notification Channel is created
        createNotificationChannel(this)

        // Handle notification payload
        message.notification?.let {
            showNotification(this, it.title ?: "New Notification", it.body ?: "FCM message received.")
        }

        // Handle data payload
        if (message.data.isNotEmpty()) {
            val title = message.data["title"] ?: "Data Notification"
            val message = message.data["message"] ?: "You received a new data message."
            showNotification(this, title, message)
        }
    }

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "fcm_channel_id"
            val channelName = "FCM Notifications"
            val descriptionText = "This channel is used for FCM notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH  // Shows notifications immediately

            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = descriptionText
                enableLights(true)
                enableVibration(true)
            }

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(context: Context, title: String, message: String) {
        val channelId = "fcm_channel_id"  // Must match the created channel
        val notificationId = System.currentTimeMillis().toInt()

        // Intent to open MainActivity when clicked
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)  // Replace with your drawable
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)  // Remove notification when clicked

        // Show the notification
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}