package com.akslabs.SandeshVahak.workers

import android.content.Context
import android.util.Log
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.akslabs.SandeshVahak.data.localdb.Preferences
// import com.akslabs.SandeshVahak.workers.InstantSmsSyncWorker // Will be replaced by SmsSyncWorker
// import com.akslabs.SandeshVahak.workers.QuickSmsSyncWorker // Removed as it's not used
import com.akslabs.SandeshVahak.workers.KeepAliveWorker
import com.akslabs.SandeshVahak.workers.SmsSyncWorker // Ensure this is imported
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
                .setInputData( // Ensure periodic sync also gets a mode, or worker handles null
                    workDataOf(SmsSyncWorker.KEY_SYNC_MODE to Preferences.SMS_SYNC_MODE_NEW_ONLY) // Default to catch-up for periodic
                )
                .build()

        fun enqueue() {
            Log.d("WorkModule.SmsSync", "enqueue() called -> unique periodic work name='$SMS_SYNC_WORK', policy=KEEP")
            manager.enqueueUniquePeriodicWork(
                SMS_SYNC_WORK,
                ExistingPeriodicWorkPolicy.KEEP,
                periodicSmsSyncRequest
            )
        }

        fun cancel() {
            Log.d("WorkModule.SmsSync", "cancel() called -> unique work name='$SMS_SYNC_WORK'")
            manager.cancelUniqueWork(SMS_SYNC_WORK)
        }

        fun enqueueOneTime(syncMode: String = SmsSyncWorker.SYNC_MODE_CATCH_UP) { // Allow specifying mode
            Log.d("WorkModule.SmsSync", "enqueueOneTime() called -> unique work name='$SMS_SYNC_ONE_TIME_WORK', mode=$syncMode, policy=REPLACE")
            val workData = workDataOf(SmsSyncWorker.KEY_SYNC_MODE to syncMode)
            val oneTimeRequest = OneTimeWorkRequestBuilder<SmsSyncWorker>()
                .setConstraints(constraints)
                .setInputData(workData)
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(5))
                .build()

            manager.enqueueUniqueWork(
                SMS_SYNC_ONE_TIME_WORK,
                ExistingWorkPolicy.REPLACE,
                oneTimeRequest
            )
        }

        fun enqueueInstant() {
            Log.d("WorkModule.SmsSync", "enqueueInstant() called for CATCH_UP -> SmsSyncWorker expedited")
            val instantConstraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val workData = workDataOf(SmsSyncWorker.KEY_SYNC_MODE to SmsSyncWorker.SYNC_MODE_CATCH_UP)

            val instantRequest = OneTimeWorkRequestBuilder<SmsSyncWorker>() // Changed from InstantSmsSyncWorker
                .setConstraints(instantConstraints)
                .setInputData(workData) // Pass the sync mode
                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(2))
                .build()

            // Using enqueueUniqueWork to prevent multiple catch-ups from new SMS in rapid succession
            manager.enqueueUniqueWork(
                INSTANT_SMS_SYNC_WORK, // New unique name for this type of work
                ExistingWorkPolicy.REPLACE, // Replace if one is already pending/running
                instantRequest
            )
        }

        // fun enqueueQuick() { ... } // Method removed

        fun enqueueStartupSync() {
            Log.d("WorkModule.SmsSync", "enqueueStartupSync() called -> StartupSyncWorker")
            val startupConstraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            // Assuming StartupSyncWorker is distinct. If not, could use SmsSyncWorker with a specific mode.
            val startupRequest = OneTimeWorkRequestBuilder<StartupSyncWorker>()
                .setConstraints(startupConstraints)
                .setInitialDelay(Duration.ofSeconds(30)) // Small delay after boot
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(5))
                .build()

            manager.enqueueUniqueWork(
                SMS_STARTUP_SYNC_WORK,
                ExistingWorkPolicy.REPLACE,
                startupRequest
            )
        }

        fun cancelOneTime() {
            manager.cancelUniqueWork(SMS_SYNC_ONE_TIME_WORK)
        }
        
        fun cancelInstant() {
            manager.cancelUniqueWork(INSTANT_SMS_SYNC_WORK)
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
    const val SMS_SYNC_WORK = "SmsSyncWork" // For periodic background sync
    const val SMS_SYNC_ONE_TIME_WORK = "SmsSyncOneTimeWork" // For manual full/catch-up syncs
    const val INSTANT_SMS_SYNC_WORK = "InstantSmsSyncWork" // For new SMS triggered catch-up
    const val SMS_SYNC_KEEP_ALIVE_WORK = "SmsSyncKeepAliveWork"
    const val SMS_STARTUP_SYNC_WORK = "SmsStartupSyncWork"
    
    val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence = "Verbose WorkManager Notifications"
    const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION = "Shows notifications whenever work starts"
    val NOTIFICATION_TITLE: CharSequence = "SandeshVahak Sync" // Corrected app name
    const val CHANNEL_ID = "VERBOSE_SANDESHVAHAK_APP_NOTIFICATION" // Corrected app name
    const val NOTIFICATION_ID = 1
}
