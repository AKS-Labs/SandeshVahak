package com.akslabs.Suchak.debug

import android.content.Context
import android.util.Log
import com.akslabs.Suchak.data.localdb.DbHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseDebugHelper {
    private const val TAG = "DatabaseDebugHelper"
    
    suspend fun debugDatabaseState(context: Context) = withContext(Dispatchers.IO) {
        try {
            Log.i(TAG, "=== DATABASE DEBUG REPORT ===")
            
            // Check database version
            val db = DbHolder.database
            Log.i(TAG, "Database version: ${db.openHelper.readableDatabase.version}")
            
            // Count records
            val allPhotos = db.photoDao().getAll()
            val allRemotePhotos = db.remotePhotoDao().getAll()
            val uploadedPhotos = allPhotos.filter { it.remoteId != null }

            // Count SMS records
            val allSmsMessages = db.smsMessageDao().getAll()
            val allRemoteSmsMessages = db.remoteSmsMessageDao().getAll()
            val syncedSmsMessages = allSmsMessages.filter { it.isSynced }

            Log.i(TAG, "Record counts:")
            Log.i(TAG, "  Total photos: ${allPhotos.size}")
            Log.i(TAG, "  Photos with remoteId: ${uploadedPhotos.size}")
            Log.i(TAG, "  Total remote photos: ${allRemotePhotos.size}")
            Log.i(TAG, "  Total SMS messages: ${allSmsMessages.size}")
            Log.i(TAG, "  Synced SMS messages: ${syncedSmsMessages.size}")
            Log.i(TAG, "  Total remote SMS messages: ${allRemoteSmsMessages.size}")
            
            // Sample data
            if (uploadedPhotos.isNotEmpty()) {
                Log.i(TAG, "Sample uploaded photos:")
                uploadedPhotos.take(3).forEach { photo ->
                    Log.i(TAG, "  Photo: localId=${photo.localId}, remoteId=${photo.remoteId}, type=${photo.photoType}")
                }
            }
            
            if (allRemotePhotos.isNotEmpty()) {
                Log.i(TAG, "Sample remote photos:")
                allRemotePhotos.take(3).forEach { remotePhoto ->
                    Log.i(TAG, "  RemotePhoto: remoteId=${remotePhoto.remoteId}, type=${remotePhoto.photoType}, fileName=${remotePhoto.fileName}")
                }
            }

            // Sample SMS data
            if (syncedSmsMessages.isNotEmpty()) {
                Log.i(TAG, "Sample synced SMS messages:")
                syncedSmsMessages.take(3).forEach { sms ->
                    Log.i(TAG, "  SMS: id=${sms.id}, remoteId=${sms.remoteId}, address=${sms.address}, syncedAt=${sms.syncedAt}")
                }
            }

            if (allRemoteSmsMessages.isNotEmpty()) {
                Log.i(TAG, "Sample remote SMS messages:")
                allRemoteSmsMessages.take(3).forEach { remoteSms ->
                    Log.i(TAG, "  RemoteSMS: remoteId=${remoteSms.remoteId}, originalId=${remoteSms.originalSmsId}, address=${remoteSms.address}")
                }
            }
            
            // Check for migration issues
            if (uploadedPhotos.isNotEmpty() && allRemotePhotos.isEmpty()) {
                Log.w(TAG, "MIGRATION ISSUE: Found uploaded photos but no remote photos!")
                Log.w(TAG, "This suggests the migration from v3 to v4 may have failed")
            }
            
            if (uploadedPhotos.size != allRemotePhotos.size) {
                Log.w(TAG, "DATA INCONSISTENCY: Uploaded photos count (${uploadedPhotos.size}) != Remote photos count (${allRemotePhotos.size})")
            }

            // Check SMS consistency
            if (syncedSmsMessages.isNotEmpty() && allRemoteSmsMessages.isEmpty()) {
                Log.w(TAG, "SMS MIGRATION ISSUE: Found synced SMS but no remote SMS!")
                Log.w(TAG, "This suggests the SMS backup/import may have failed")
            }

            if (syncedSmsMessages.size != allRemoteSmsMessages.size) {
                Log.w(TAG, "SMS DATA INCONSISTENCY: Synced SMS count (${syncedSmsMessages.size}) != Remote SMS count (${allRemoteSmsMessages.size})")
            }
            
            Log.i(TAG, "=== END DATABASE DEBUG REPORT ===")
            
        } catch (e: Exception) {
            Log.e(TAG, "Error during database debug", e)
        }
    }
}
