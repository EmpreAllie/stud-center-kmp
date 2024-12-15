package com.studcenter.root

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.studcenter.data.utils.Log
import com.studcenter.data.utils.localize
import com.studcenter.domain.constants.Constants
import com.studcenter.resources.MultiplatformResource
import dev.icerock.moko.network.generated.apis.NotificationApi
import dev.icerock.moko.network.generated.models.UpdateFcmTokenRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin

class StudyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val notificationApi: NotificationApi = getKoin().get()
                val updateFcmTokenRequest = UpdateFcmTokenRequest(fcmToken = token)
                notificationApi.apiV1NotificationFcmTokenPost(updateFcmTokenRequest)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)


        remoteMessage.data.let {
            val title = it["tittle"]
            val body = it["body"]
            showNotification(title, body)
        }
    }

    private fun showNotification(title: String?, body: String?) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.Strings.NOTIFICATION.idChannel,
                Constants.Strings.NOTIFICATION.nameChannel,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Уведомления о событиях"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, Constants.Strings.NOTIFICATION.idChannel)
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        val notificationId = (System.currentTimeMillis() % 10000).toInt()

        notificationManager.notify(notificationId, notification)
    }
}