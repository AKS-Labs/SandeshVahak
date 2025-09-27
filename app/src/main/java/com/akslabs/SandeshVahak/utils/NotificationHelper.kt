package com.akslabs.SandeshVahak.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.akslabs.SandeshVahak.R
import com.akslabs.SandeshVahak.ui.MainActivity

object NotificationHelper {

    private const val TAG = "NotificationHelper"
    private const val CHANNEL_ID_SYNC_SERVICE = "SmsObserverServiceChannel" // Matches Service's channel ID
    private const val CHANNEL_NAME_SYNC_SERVICE = "SMS Sync Service"
    private const val CHANNEL_ID_POST_BOOT = "PostBootNotificationChannel"
    private const val CHANNEL_NAME_POST_BOOT = "App Status Notifications"
    private const val NOTIFICATION_ID_POST_BOOT = 1002
    private const val REQUEST_CODE_POST_BOOT = 2

    // New constants for service start failure notification
    private const val NOTIFICATION_ID_SERVICE_START_FAILED = 1003
    private const val REQUEST_CODE_SERVICE_START_FAILED = 3


    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Channel for the main foreground service (SmsObserverService)
            val serviceChannel = NotificationChannel(
                CHANNEL_ID_SYNC_SERVICE,
                CHANNEL_NAME_SYNC_SERVICE,
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Notifications for the active SMS Sync service."
                //setShowBadge(false) // No badge for ongoing low-priority service
            }
            notificationManager.createNotificationChannel(serviceChannel)
            Log.d(TAG, "Notification channel $CHANNEL_ID_SYNC_SERVICE created/ensured.")

            // Channel for post-boot/app status notifications (higher importance to alert user)
            val postBootChannel = NotificationChannel(
                CHANNEL_ID_POST_BOOT,
                CHANNEL_NAME_POST_BOOT,
                NotificationManager.IMPORTANCE_HIGH // Higher importance to alert user
            ).apply {
                description = "Notifications about app status after boot or for important alerts."
            }
            notificationManager.createNotificationChannel(postBootChannel)
            Log.d(TAG, "Notification channel $CHANNEL_ID_POST_BOOT created/ensured.")
        }
    }

    fun showPostBootNotification(context: Context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.w(TAG, "Cannot show post-boot notification, POST_NOTIFICATIONS permission missing.")
            return
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannels(context) // Ensure channel exists

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getActivity(context, REQUEST_CODE_POST_BOOT, intent, pendingIntentFlags)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_POST_BOOT)
            .setSmallIcon(R.drawable.icon) // Replace with your app's icon
            .setContentTitle("SandeshVahak Ready")
            .setContentText("SMS Sync service is configured to run. Tap to open.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(NOTIFICATION_ID_POST_BOOT, notification)
        Log.i(TAG, "Post-boot notification shown.")
    }

    // New function for service start failure
    fun showServiceStartFailedNotification(context: Context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.w(TAG, "Cannot show service start failed notification, POST_NOTIFICATIONS permission missing.")
            return
        }

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannels(context) // Ensure channel exists

        val intent = Intent(context, MainActivity::class.java).apply { // Or a settings/help activity
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            //putExtra("error_type", "service_start_failed") // Optional: pass info to activity
        }
        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getActivity(context, REQUEST_CODE_SERVICE_START_FAILED, intent, pendingIntentFlags)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID_POST_BOOT) // Reusing post_boot channel
            .setSmallIcon(R.drawable.icon) // Ensure this drawable exists!
            .setContentTitle("SandeshVahak Service Issue")
            .setContentText("Could not start SMS sync service automatically. Tap for details.")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("The SMS sync service could not be started automatically after boot. Please open the app to check its status or re-enable sync if needed."))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(NOTIFICATION_ID_SERVICE_START_FAILED, notification)
        Log.i(TAG, "Service start failed notification shown.")
    }

    // Example of how you might create a notification for a foreground service (like SmsSyncWorker)
    // This is NOT for SmsObserverService's own notification (that's built in the service itself)
    // but rather for the Worker if it needs to show one.
    fun createSyncWorkerNotificationBuilder(context: Context, contentText: String): NotificationCompat.Builder {
         createNotificationChannels(context) // Ensure channel used by worker exists

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, pendingIntentFlags)

        // Assuming SmsSyncWorker uses a channel like CHANNEL_ID_SYNC_SERVICE or its own.
        // For example, if it uses CHANNEL_ID_POST_BOOT or a dedicated worker channel.
        return NotificationCompat.Builder(context, CHANNEL_ID_POST_BOOT) // Or worker-specific channel
            .setContentTitle("SMS Sync in Progress (Worker)")
            .setContentText(contentText)
            .setSmallIcon(R.drawable.cloud_arrow_up_solid) // Worker's icon
            .setOngoing(true)
            .setAutoCancel(false)
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .setContentIntent(pendingIntent)
            .setSilent(true)
    }
}
