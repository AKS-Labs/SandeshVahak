package com.akslabs.SandeshVahak.workers // Corrected package declaration

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.akslabs.SandeshVahak.R // Corrected R import
import com.akslabs.SandeshVahak.workers.WorkModule.CHANNEL_ID // Corrected import
import com.akslabs.SandeshVahak.workers.WorkModule.NOTIFICATION_TITLE // Corrected import
import com.akslabs.SandeshVahak.workers.WorkModule.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION // Corrected import
import com.akslabs.SandeshVahak.workers.WorkModule.VERBOSE_NOTIFICATION_CHANNEL_NAME // Corrected import

private const val TAG = "WorkerUtils"

/**
 * Create a Notification that is shown as a heads-up notification if possible.
 * @param message Message shown on the notification
 * @param context Context needed to create Toast
 */
fun makeStatusNotification(message: String, context: Context): Notification {
    val name = VERBOSE_NOTIFICATION_CHANNEL_NAME
    val description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION
    val importance = NotificationManager.IMPORTANCE_HIGH
    val channel = NotificationChannel(CHANNEL_ID, name, importance)
    channel.description = description

    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?

    notificationManager?.createNotificationChannel(channel)

    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.icon)
        .setContentTitle(NOTIFICATION_TITLE)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(LongArray(0))

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            builder.build()
        }
    }
    return builder.build()
}