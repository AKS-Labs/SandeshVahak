package com.akslabs.SandeshVahak.data.localdb.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration6to7_FixSchemaIntegrity : Migration(6, 7) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // This migration fixes schema integrity issues after photo table removal
        // Ensure photo tables are completely removed if they still exist
        database.execSQL("DROP TABLE IF EXISTS Photo")
        database.execSQL("DROP TABLE IF EXISTS RemotePhoto")
        
        // Verify SMS tables exist with correct schema
        // If they don't exist, create them (this handles edge cases)
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS SmsMessage (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                address TEXT NOT NULL,
                body TEXT NOT NULL,
                date INTEGER NOT NULL,
                type INTEGER NOT NULL,
                read INTEGER NOT NULL,
                threadId INTEGER NOT NULL,
                remoteId INTEGER,
                syncedAt INTEGER
            )
        """)
        
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS RemoteSmsMessage (
                remoteId INTEGER PRIMARY KEY NOT NULL,
                originalId INTEGER,
                address TEXT NOT NULL,
                body TEXT NOT NULL,
                date INTEGER NOT NULL,
                type INTEGER NOT NULL,
                read INTEGER NOT NULL,
                threadId INTEGER NOT NULL,
                receivedAt INTEGER NOT NULL
            )
        """)
        
        // Create indices if they don't exist
        database.execSQL("CREATE INDEX IF NOT EXISTS index_SmsMessage_remoteId ON SmsMessage(remoteId)")
        database.execSQL("CREATE INDEX IF NOT EXISTS index_SmsMessage_syncedAt ON SmsMessage(syncedAt)")
        database.execSQL("CREATE INDEX IF NOT EXISTS index_SmsMessage_date ON SmsMessage(date)")
        database.execSQL("CREATE INDEX IF NOT EXISTS index_RemoteSmsMessage_originalId ON RemoteSmsMessage(originalId)")
        database.execSQL("CREATE INDEX IF NOT EXISTS index_RemoteSmsMessage_date ON RemoteSmsMessage(date)")
    }
}
