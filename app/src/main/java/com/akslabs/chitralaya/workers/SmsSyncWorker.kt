package com.akslabs.SandeshVahak.workers

import android.content.Context
import android.content.pm.ServiceInfo
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.akslabs.SandeshVahak.R
import com.akslabs.SandeshVahak.services.SmsSyncService
import com.akslabs.SandeshVahak.services.SmsSyncResult
import com.akslabs.SandeshVahak.utils.NotificationHelper
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import com.akslabs.SandeshVahak.data.localdb.Preferences // Added import for clarity, though FQNs are being replaced

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

        // Ensure preferences are initialized
        try {
            Preferences.init(applicationContext)
            Log.d(TAG, "Preferences initialized successfully in SmsSyncWorker")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize preferences in SmsSyncWorker", e)
        }

        // Respect user preference; if disabled, cancel gracefully
        val isEnabled = try {
            Preferences.getBoolean(
                Preferences.isSmsSyncEnabledKey,
                false
            )
        } catch (e: Exception) {
            Log.e(TAG, "Failed to read SMS sync enabled preference in worker, defaulting to false", e)
            false
        }

        if (!isEnabled) {
            Log.w(TAG, "SMS sync disabled by user; skipping work")
            return Result.success()
        } else {
            Log.d(TAG, "SMS sync enabled; proceeding with work")
        }

        // Check sync mode to determine if we should bypass timestamp check
        val syncMode = try {
            Preferences.getString(
                Preferences.smsSyncModeKey,
                "ALL"
            )
        } catch (e: Exception) {
            Log.w(TAG, "Failed to read sync mode, defaulting to ALL", e)
            "ALL"
        }
        
        Log.d(TAG, "Current sync mode: $syncMode")

        return try {
            // Set foreground info for long-running operation
            setForeground(createForegroundInfo())

            // Perform sync - for ALL mode, we want to ensure existing messages are synced
            var finalSyncResult: SmsSyncResult? = null
            Log.d(TAG, "Collecting progress from performFullSync()")
            
            // If in ALL mode, force a sync to ensure existing messages are processed
            val syncFlow = if (syncMode == "ALL") {
                Log.i(TAG, "ALL mode detected - forcing full sync to process existing messages")
                SmsSyncService.forceSync(applicationContext)
            } else {
                // For NEW_ONLY mode, use normal sync with timestamp check
                SmsSyncService.performFullSync(applicationContext)
            }
            
            syncFlow.collect { progress ->
                Log.d(TAG, "Worker progress update: batch=${progress.currentBatch}, total=${progress.totalMessagesSynced}, complete=${progress.isComplete}, error=${progress.errorMessage}")
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
                        Log.d(TAG, "Attempting to show completion notification for $syncedMessages messages")
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
            try {
                // Ensure keep-alive is scheduled
                // Assuming WorkModule's Preferences usage will be handled separately if needed
                com.akslabs.SandeshVahak.workers.WorkModule.SmsSync.enqueueKeepAlive()
            } catch (e: Exception) {
                Log.w(TAG, "Failed to enqueue keep-alive worker", e)
            }
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
        val isEnabled = Preferences.getBoolean(
            Preferences.isSmsSyncEnabledKey,
            false
        )
        if (!isEnabled) {
            Log.w(TAG, "SMS sync disabled by user; skipping quick sync")
            return Result.success()
        }

        return try {
            Log.d(TAG, "Invoking performQuickSync() from QuickSmsSyncWorker")
            val syncResult = SmsSyncService.performQuickSync(applicationContext)
            Log.d(TAG, "performQuickSync() returned $syncResult")

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
        val isEnabled = Preferences.getBoolean(
            Preferences.isSmsSyncEnabledKey,
            false
        )
        
        // Get sync mode for debugging
        val syncMode = try {
            Preferences.getString(
                Preferences.smsSyncModeKey,
                "ALL"
            )
        } catch (e: Exception) {
            "ALL"
        }
        
        val baseline = try {
            Preferences.getLong(
                Preferences.smsSyncEnabledSinceKey,
                0L
            )
        } catch (e: Exception) {
            0L
        }
        
        Log.d(TAG, "Instant sync worker - Enabled: $isEnabled, Mode: $syncMode, Baseline: $baseline")
        
        if (!isEnabled) {
            Log.w(TAG, "SMS sync disabled by user; skipping instant sync")
            return Result.success()
        }

        return try {
            Log.d(TAG, "Invoking performQuickSync() from InstantSmsSyncWorker")
            val syncResult = SmsSyncService.performQuickSync(applicationContext)
            Log.d(TAG, "performQuickSync() returned $syncResult")

            when (syncResult) {
                is SmsSyncResult.Success -> {
                    val syncedCount = syncResult.messagesSynced
                    Log.d(TAG, "Instant sync completed: $syncedCount messages synced")

                    if (syncedCount > 0) {
                        Log.d(TAG, "Attempting to show instant sync notification for $syncedCount messages")
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
        const val KEY_TARGET_IDS = "target_ids"
    }
}
