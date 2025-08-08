package com.akslabs.chitralaya.workers

import android.content.Context
import android.content.pm.ServiceInfo
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.akslabs.SandeshVahak.R
import com.akslabs.chitralaya.services.SmsSyncService
import com.akslabs.chitralaya.services.SmsSyncResult
import com.akslabs.SandeshVahak.utils.NotificationHelper

/**
 * Background worker for syncing SMS messages to Telegram channel
 * Runs periodically to sync unsynced SMS messages
 */
class SmsSyncWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.i(TAG, "=== SMS SYNC WORKER STARTED ===")

        // Respect user preference; if disabled, cancel gracefully
        val isEnabled = com.akslabs.SandeshVahak.data.localdb.Preferences.getBoolean(
            com.akslabs.SandeshVahak.data.localdb.Preferences.isSmsSyncEnabledKey,
            false
        )
        if (!isEnabled) {
            Log.w(TAG, "SMS sync disabled by user; skipping work")
            return Result.success()
        }

        return try {
            // Set foreground info for long-running operation
            setForeground(createForegroundInfo())

            // Perform sync
            var finalSyncResult: SmsSyncResult? = null
            SmsSyncService.performFullSync(applicationContext).collect { progress ->
                // Update progress in foreground notification
                if (progress.isComplete) {
                    finalSyncResult = if (progress.errorMessage != null) {
                        SmsSyncResult.Error(progress.errorMessage)
                    } else {
                        SmsSyncResult.Success(progress.totalMessagesSynced)
                    }
                } else {
                    // Update notification with progress
                    setProgress(
                        androidx.work.workDataOf(
                            "batch" to progress.currentBatch,
                            "synced" to progress.totalMessagesSynced
                        )
                    )
                }
            }

            when (val result = finalSyncResult) {
                is SmsSyncResult.Success -> {
                    val syncedMessages = result.messagesSynced
                    Log.i(TAG, "SMS sync completed successfully: $syncedMessages messages synced")
                    if (syncedMessages > 0) {
                        // Show notification about synced messages
                        NotificationHelper.showSmsSyncCompleteNotification(
                            applicationContext,
                            syncedMessages
                        )
                    }
                    Result.success()
                }

                is SmsSyncResult.Error -> {
                    Log.e(TAG, "SMS sync failed: ${result.message}")
                    Result.failure()
                }

                is SmsSyncResult.NoChannelConfigured -> {
                    Log.w(TAG, "No Telegram channel configured, skipping sync")
                    Result.success() // Not a failure, just nothing to do
                }

                null -> {
                    Log.e(TAG, "Sync result was null")
                    Result.failure()
                }
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Exception in SmsSyncWorker", e)
            Result.failure()
        } finally {
            Log.i(TAG, "=== SMS SYNC WORKER FINISHED ===")
        }
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return createForegroundInfo()
    }

    private fun createForegroundInfo(): ForegroundInfo {
        val notification = NotificationHelper.createSmsSyncNotification(
            applicationContext,
            "Syncing SMS messages...",
            "Uploading SMS messages to Telegram"
        )
        
        return ForegroundInfo(
            NOTIFICATION_ID,
            notification,
            ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
        )
    }

    companion object {
        private const val TAG = "SmsSyncWorker"
        private const val NOTIFICATION_ID = 2002
    }
}

/**
 * Quick SMS sync worker for frequent checks
 */
class QuickSmsSyncWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.d(TAG, "Quick SMS sync worker started")

        // Respect user preference; if disabled, cancel gracefully
        val isEnabled = com.akslabs.SandeshVahak.data.localdb.Preferences.getBoolean(
            com.akslabs.SandeshVahak.data.localdb.Preferences.isSmsSyncEnabledKey,
            false
        )
        if (!isEnabled) {
            Log.w(TAG, "SMS sync disabled by user; skipping quick sync")
            return Result.success()
        }

        return try {
            val syncResult = SmsSyncService.performQuickSync(applicationContext)

            when (syncResult) {
                is SmsSyncResult.Success -> {
                    Log.d(TAG, "Quick sync completed: ${syncResult.messagesSynced} messages synced")
                    Result.success()
                }

                is SmsSyncResult.Error -> {
                    Log.e(TAG, "Quick sync failed: ${syncResult.message}")
                    Result.retry()
                }

                is SmsSyncResult.NoChannelConfigured -> {
                    Log.d(TAG, "No channel configured for quick sync")
                    Result.success()
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, "Exception in QuickSmsSyncWorker", e)
            Result.retry()
        }
    }

    companion object {
        private const val TAG = "QuickSmsSyncWorker"
    }
}

/**
 * Instant SMS sync worker for immediate syncing of new messages
 */
class InstantSmsSyncWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.d(TAG, "Instant SMS sync worker started")

        // Respect user preference; if disabled, cancel gracefully
        val isEnabled = com.akslabs.SandeshVahak.data.localdb.Preferences.getBoolean(
            com.akslabs.SandeshVahak.data.localdb.Preferences.isSmsSyncEnabledKey,
            false
        )
        if (!isEnabled) {
            Log.w(TAG, "SMS sync disabled by user; skipping instant sync")
            return Result.success()
        }

        return try {
            val syncResult = SmsSyncService.performQuickSync(applicationContext)

            when (syncResult) {
                is SmsSyncResult.Success -> {
                    val syncedCount = syncResult.messagesSynced
                    Log.d(TAG, "Instant sync completed: $syncedCount messages synced")

                    if (syncedCount > 0) {
                        // Show notification for instant sync
                        NotificationHelper.showInstantSmsSyncNotification(
                            applicationContext,
                            syncedCount
                        )
                    }

                    Result.success()
                }

                is SmsSyncResult.Error -> {
                    Log.e(TAG, "Instant sync failed: ${syncResult.message}")
                    Result.failure()
                }

                is SmsSyncResult.NoChannelConfigured -> {
                    Log.d(TAG, "No channel configured for instant sync")
                    Result.success()
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, "Exception in InstantSmsSyncWorker", e)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "InstantSmsSyncWorker"
    }
}
