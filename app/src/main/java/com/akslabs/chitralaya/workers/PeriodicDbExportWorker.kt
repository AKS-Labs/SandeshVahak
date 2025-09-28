package com.akslabs.SandeshVahak.workers // Corrected package declaration

import android.content.Context
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
import android.util.Log
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.akslabs.SandeshVahak.R // Corrected R import
import com.akslabs.SandeshVahak.data.localdb.backup.BackupHelper // Corrected import
import com.akslabs.SandeshVahak.utils.toastFromMainThread // Corrected import
import com.akslabs.SandeshVahak.workers.WorkModule.NOTIFICATION_ID // Corrected import
import com.akslabs.SandeshVahak.workers.makeStatusNotification // Corrected import
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PeriodicDbExportWorker(
    private val appContext: Context,
    private val params: WorkerParameters,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.Default) {
            try {
                val uri = params.inputData.getString(KEY_URI)!!
                BackupHelper.exportDatabase(uri.toUri(), appContext)
                Result.success()
            } catch (e: Exception) {
                Log.d("PeriodicDbExportWorker", "doWork: ${e.localizedMessage}")
                appContext.toastFromMainThread(e.localizedMessage)
                Result.failure()
            }
        }
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(
            NOTIFICATION_ID,
            makeStatusNotification(appContext.getString(R.string.exporting_database), appContext),
            FOREGROUND_SERVICE_TYPE_DATA_SYNC
        )
    }

    companion object {
        const val KEY_URI = "uri"
    }
}