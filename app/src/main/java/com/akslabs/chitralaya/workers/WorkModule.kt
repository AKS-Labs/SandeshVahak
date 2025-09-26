package com.akslabs.SandeshVahak.workers

import android.content.Context


import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.akslabs.SandeshVahak.data.localdb.Preferences
import com.akslabs.SandeshVahak.workers.SmsSyncWorker
import com.akslabs.SandeshVahak.workers.InstantSmsSyncWorker
import com.akslabs.SandeshVahak.workers.QuickSmsSyncWorker
import com.akslabs.SandeshVahak.workers.KeepAliveWorker
import com.akslabs.SandeshVahak.workers.StartupSyncWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import java.time.Duration

object WorkModule {
    private lateinit var manager: WorkManager
    fun create(applicationContext: Context) {
        manager = WorkManager.getInstance(applicationContext)
    }

    object PeriodicDbExport {

        private val repeatIntervalDays = Preferences.getString(
            Preferences.autoExportDatabaseIntervalKey,
            Preferences.defaultAutoBackupInterval.toString()
        ).toLong()

        private val uri = Preferences.getString(
            Preferences.autoExportDatabseLocation,
            ""
        )

        private val periodicDbExportRequest =
            PeriodicWorkRequestBuilder<PeriodicDbExportWorker>(Duration.ofDays(repeatIntervalDays))
                .setInputData(
                    workDataOf(PeriodicDbExportWorker.KEY_URI to uri)
                )
                .setBackoffCriteria(
                    backoffPolicy = BackoffPolicy.EXPONENTIAL,
                    duration = Duration.ofMinutes(10)
                )
                .build()

        fun enqueue(forceUpdate: Boolean = false) {
            manager.enqueueUniquePeriodicWork(
                PERIODIC_DB_EXPORT_WORK,
                if (forceUpdate) ExistingPeriodicWorkPolicy.UPDATE else ExistingPeriodicWorkPolicy.KEEP,
                periodicDbExportRequest
            )
        }

        fun cancel() {
            manager.cancelUniqueWork(PERIODIC_DB_EXPORT_WORK)
        }
    }

    object DailyDatabaseBackup {
        private val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        private val dailyBackupRequest = PeriodicWorkRequestBuilder<DailyDatabaseBackupWorker>(
            Duration.ofDays(1)
        )
            .setConstraints(constraints)
            .setInitialDelay(Duration.ofHours(1)) // Start 1 hour after app install
            .build()

        fun enqueuePeriodic() {
            manager.enqueueUniquePeriodicWork(
                DAILY_DATABASE_BACKUP_WORK,
                ExistingPeriodicWorkPolicy.KEEP,
                dailyBackupRequest
            )
        }

        fun cancel() {
            manager.cancelUniqueWork(DAILY_DATABASE_BACKUP_WORK)
        }
    }

    fun observeWorkerByName(name: String) = manager.getWorkInfosForUniqueWorkFlow(name)
        .flowOn(Dispatchers.IO)

    // SMS Sync Work Modules
    object SmsSync {
        private val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        private val periodicSmsSyncRequest =
            PeriodicWorkRequestBuilder<SmsSyncWorker>(Duration.ofHours(6))
                .setConstraints(constraints)
                .setInitialDelay(Duration.ofMinutes(15)) // Wait 15 minutes after app install
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(15))
                .build()

        fun enqueue() {
            android.util.Log.d("WorkModule.SmsSync", "enqueue() called -> unique periodic work name='${SMS_SYNC_WORK}', policy=KEEP")
            manager.enqueueUniquePeriodicWork(
                SMS_SYNC_WORK,
                ExistingPeriodicWorkPolicy.KEEP,
                periodicSmsSyncRequest
            )
        }

        fun cancel() {
            android.util.Log.d("WorkModule.SmsSync", "cancel() called -> unique work name='${SMS_SYNC_WORK}'")
            manager.cancelUniqueWork(SMS_SYNC_WORK)
        }

        fun enqueueOneTime() {
            android.util.Log.d("WorkModule.SmsSync", "enqueueOneTime() called -> unique work name='${SMS_SYNC_ONE_TIME_WORK}', policy=REPLACE")
            val oneTimeRequest = OneTimeWorkRequestBuilder<SmsSyncWorker>()
                .setConstraints(constraints)
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(5))
                .build()

            manager.enqueueUniqueWork(
                SMS_SYNC_ONE_TIME_WORK,
                ExistingWorkPolicy.REPLACE,
                oneTimeRequest
            )
        }

        fun enqueueInstant() {
            android.util.Log.d("WorkModule.SmsSync", "enqueueInstant() called -> InstantSmsSyncWorker expedited")
            val instantConstraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val instantRequest = OneTimeWorkRequestBuilder<InstantSmsSyncWorker>()
                .setConstraints(instantConstraints)
                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(2))
                .build()

            manager.enqueue(instantRequest)
        }

        fun enqueueQuick() {
            android.util.Log.d("WorkModule.SmsSync", "enqueueQuick() called -> QuickSmsSyncWorker expedited")
            val quickConstraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val quickRequest = OneTimeWorkRequestBuilder<QuickSmsSyncWorker>()
                .setConstraints(quickConstraints)
                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(2))
                .build()

            manager.enqueue(quickRequest)
        }

        fun enqueueStartupSync() {
            android.util.Log.d("WorkModule.SmsSync", "enqueueStartupSync() called -> StartupSyncWorker")
            val startupConstraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val startupRequest = OneTimeWorkRequestBuilder<StartupSyncWorker>()
                .setConstraints(startupConstraints)
                .setInitialDelay(Duration.ofSeconds(30)) // Small delay after boot
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(5))
                .build()

            manager.enqueueUniqueWork(
                SMS_STARTUP_SYNC_WORK, // Added new unique name
                ExistingWorkPolicy.REPLACE, // Replace if already pending
                startupRequest
            )
        }

        fun cancelOneTime() {
            manager.cancelUniqueWork(SMS_SYNC_ONE_TIME_WORK)
        }

        // Lightweight keep-alive periodic ping to ensure scheduling persists across OS constraints
        fun enqueueKeepAlive() {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val request = PeriodicWorkRequestBuilder<KeepAliveWorker>(Duration.ofMinutes(15))
                .setConstraints(constraints)
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(5))
                .build()

            manager.enqueueUniquePeriodicWork(
                SMS_SYNC_KEEP_ALIVE_WORK,
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
        }

        fun cancelKeepAlive() {
            manager.cancelUniqueWork(SMS_SYNC_KEEP_ALIVE_WORK)
        }
    }

    const val PERIODIC_DB_EXPORT_WORK = "PeriodicDbExportWork"
    const val DAILY_DATABASE_BACKUP_WORK = "DailyDatabaseBackupWork"

    // SMS Sync Work Constants
    const val SMS_SYNC_WORK = "SmsSyncWork"
    const val SMS_SYNC_ONE_TIME_WORK = "SmsSyncOneTimeWork"
    const val SMS_SYNC_KEEP_ALIVE_WORK = "SmsSyncKeepAliveWork"
    const val SMS_STARTUP_SYNC_WORK = "SmsStartupSyncWork"
    
    val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence = "Verbose WorkManager Notifications"
    const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION = "Shows notifications whenever work starts"
    val NOTIFICATION_TITLE: CharSequence = "Chitralaya Sync" // Corrected value
    const val CHANNEL_ID = "VERBOSE_CHITRALAYA_APP_NOTIFICATION" // Corrected value
    const val NOTIFICATION_ID = 1
}