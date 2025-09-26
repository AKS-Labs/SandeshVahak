package com.akslabs.SandeshVahak.data.localdb.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration5to6_RemovePhotoTables : Migration(5, 6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Drop photo-related tables if they exist
        database.execSQL("DROP TABLE IF EXISTS Photo")
        database.execSQL("DROP TABLE IF EXISTS RemotePhoto")
        
        // Note: SMS tables (SmsMessage and RemoteSmsMessage) remain unchanged
        // This migration only removes photo functionality
    }
}
