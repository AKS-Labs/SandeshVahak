package com.akslabs.Suchak.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.akslabs.SandeshVahak.R
import com.akslabs.chitralaya.ui.MainActivity

object NotificationHelper {

    // Cloud sync notification constants
    private const val CLOUD_SYNC_CHANNEL_ID = "cloud_sync_channel"
    private const val CLOUD_SYNC_CHANNEL_NAME = "Cloud Sync"
    private const val CLOUD_SYNC_CHANNEL_DESCRIPTION = "Notifications for cloud synchronization"
    private const val CLOUD_SYNC_NOTIFICATION_ID = 1001
    private const val CLOUD_SYNC_ERROR_NOTIFICATION_ID = 1002

    /**
     * Create notification channels for all app notifications
     */
    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Cloud sync channel
            val cloudSyncChannel = NotificationChannel(
                CLOUD_SYNC_CHANNEL_ID,
                CLOUD_SYNC_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = CLOUD_SYNC_CHANNEL_DESCRIPTION
                setShowBadge(false)
            }

            // SMS sync channel
            val smsSyncChannel = NotificationChannel(
                SMS_SYNC_CHANNEL_ID,
                SMS_SYNC_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = SMS_SYNC_CHANNEL_DESCRIPTION
                setShowBadge(false)
            }

            // SMS observer channel
            val smsObserverChannel = NotificationChannel(
                SMS_OBSERVER_CHANNEL_ID,
                SMS_OBSERVER_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_MIN
            ).apply {
                description = SMS_OBSERVER_CHANNEL_DESCRIPTION
                setShowBadge(false)
            }

            // Post-boot notification channel (higher importance)
            val postBootChannel = NotificationChannel(
                POST_BOOT_CHANNEL_ID,
                POST_BOOT_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = POST_BOOT_CHANNEL_DESCRIPTION
                setShowBadge(false)
            }

            notificationManager.createNotificationChannel(cloudSyncChannel)
            notificationManager.createNotificationChannel(smsSyncChannel)
            notificationManager.createNotificationChannel(smsObserverChannel)
            notificationManager.createNotificationChannel(postBootChannel)
        }
    }

    /**
     * Create notification for ongoing cloud sync operation
     */
    fun createCloudSyncNotification(
        context: Context,
        title: String,
        content: String
    ): Notification {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(context, CLOUD_SYNC_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .build()
    }

    /**
     * Create notification for cloud sync completion
     */
    fun createCloudSyncCompleteNotification(
        context: Context,
        title: String,
        content: String
    ): Notification {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(context, CLOUD_SYNC_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_STATUS)
            .build()
    }

    /**
     * Show notification when cloud sync completes
     */
    fun showCloudSyncCompleteNotification(context: Context, photoCount: Int) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = createCloudSyncCompleteNotification(
            context,
            "Cloud Sync Complete",
            "Successfully synced $photoCount photos to cloud"
        )

        notificationManager.notify(CLOUD_SYNC_NOTIFICATION_ID, notification)
    }

    /**
     * Show notification when cloud sync encounters an error
     */
    fun showCloudSyncErrorNotification(context: Context, errorMessage: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CLOUD_SYNC_CHANNEL_ID)
            .setContentTitle("Cloud Sync Error")
            .setContentText(errorMessage)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ERROR)
            .build()

        notificationManager.notify(CLOUD_SYNC_ERROR_NOTIFICATION_ID, notification)
    }

    // SMS Sync Notification Constants
    private const val SMS_SYNC_CHANNEL_ID = "sms_sync_channel"
    private const val SMS_SYNC_CHANNEL_NAME = "SMS Sync"
    private const val SMS_SYNC_CHANNEL_DESCRIPTION = "Notifications for SMS synchronization"
    private const val SMS_OBSERVER_CHANNEL_ID = "sms_observer_channel"
    private const val SMS_OBSERVER_CHANNEL_NAME = "SMS Observer"
    private const val SMS_OBSERVER_CHANNEL_DESCRIPTION = "Background SMS monitoring service"
    private const val SMS_SYNC_COMPLETE_NOTIFICATION_ID = 3002
    private const val SMS_SYNC_INSTANT_NOTIFICATION_ID = 3003
    // Post-boot prompt channel
    private const val POST_BOOT_CHANNEL_ID = "post_boot_channel"
    private const val POST_BOOT_CHANNEL_NAME = "Post Boot"
    private const val POST_BOOT_CHANNEL_DESCRIPTION = "Prompt to resume background operation after reboot"

    /**
     * Create notification for ongoing SMS sync operation
     */
    fun createSmsSyncNotification(
        context: Context,
        title: String,
        content: String
    ): Notification {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(context, SMS_SYNC_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .build()
    }

    /**
     * Create notification for SMS observer service
     */
    fun createSmsObserverNotification(
        context: Context,
        title: String,
        content: String
    ): Notification {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(context, SMS_OBSERVER_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .build()
    }

    /**
     * Show notification when SMS sync completes
     */
    fun showSmsSyncCompleteNotification(context: Context, messageCount: Int) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, SMS_SYNC_CHANNEL_ID)
            .setContentTitle("SMS Sync Complete")
            .setContentText("Successfully synced $messageCount SMS messages to Telegram")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_STATUS)
            .build()

        notificationManager.notify(SMS_SYNC_COMPLETE_NOTIFICATION_ID, notification)
    }

    /**
     * Show notification for instant SMS sync
     */
    fun showInstantSmsSyncNotification(context: Context, messageCount: Int) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, SMS_SYNC_CHANNEL_ID)
            .setContentTitle("New SMS Synced")
            .setContentText("$messageCount new SMS messages synced to Telegram")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setCategory(NotificationCompat.CATEGORY_STATUS)
            .build()

        notificationManager.notify(SMS_SYNC_INSTANT_NOTIFICATION_ID, notification)
    }

    fun showPostBootNotification(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, POST_BOOT_CHANNEL_ID)
            .setContentTitle("Open SandeshVahak to resume SMS sync")
            .setContentText("Android 15 restricts background startup. Tap to reopen the app.")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(4001, notification)
    }
}
