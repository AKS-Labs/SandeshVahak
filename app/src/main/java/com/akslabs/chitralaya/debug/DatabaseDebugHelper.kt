package com.akslabs.SandeshVahak.debug

import android.content.Context
import android.util.Log
import com.akslabs.SandeshVahak.data.localdb.DbHolder
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
            
            // Count SMS records
            val allSmsMessages = db.smsMessageDao().getAll()
            val allRemoteSmsMessages = db.remoteSmsMessageDao().getAll()
            val syncedSmsMessages = allSmsMessages.filter { it.isSynced }

            Log.i(TAG, "Record counts:")
            Log.i(TAG, "  Total SMS messages: ${allSmsMessages.size}")
            Log.i(TAG, "  Synced SMS messages: ${syncedSmsMessages.size}")
            Log.i(TAG, "  Total remote SMS messages: ${allRemoteSmsMessages.size}")

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
