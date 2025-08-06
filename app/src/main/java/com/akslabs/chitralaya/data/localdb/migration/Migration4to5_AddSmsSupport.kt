package com.akslabs.chitralaya.data.localdb.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration4to5_AddSmsSupport : Migration(4, 5) {

    override fun migrate(db: SupportSQLiteDatabase) {
        // Create SMS messages table
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `sms_messages` (
                `id` TEXT NOT NULL,
                `threadId` TEXT NOT NULL,
                `address` TEXT NOT NULL,
                `person` TEXT,
                `date` INTEGER NOT NULL,
                `dateSent` INTEGER,
                `protocol` TEXT,
                `read` INTEGER NOT NULL DEFAULT 0,
                `status` INTEGER NOT NULL DEFAULT -1,
                `type` INTEGER NOT NULL,
                `replyPathPresent` INTEGER NOT NULL DEFAULT 0,
                `subject` TEXT,
                `body` TEXT NOT NULL,
                `serviceCenter` TEXT,
                `locked` INTEGER NOT NULL DEFAULT 0,
                `errorCode` INTEGER NOT NULL DEFAULT 0,
                `seen` INTEGER NOT NULL DEFAULT 0,
                `remoteId` TEXT,
                `isSynced` INTEGER NOT NULL DEFAULT 0,
                `syncedAt` INTEGER,
                `syncAttempts` INTEGER NOT NULL DEFAULT 0,
                `lastSyncAttempt` INTEGER,
                `syncError` TEXT,
                PRIMARY KEY(`id`)
            )
            """.trimIndent()
        )

        // Create remote SMS messages table
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `remote_sms_messages` (
                `remoteId` TEXT NOT NULL,
                `originalSmsId` TEXT NOT NULL,
                `address` TEXT NOT NULL,
                `body` TEXT NOT NULL,
                `date` INTEGER NOT NULL,
                `type` INTEGER NOT NULL,
                `syncedAt` INTEGER NOT NULL,
                `telegramChannelId` INTEGER,
                `telegramMessageId` INTEGER,
                PRIMARY KEY(`remoteId`)
            )
            """.trimIndent()
        )

        // Create indexes for better performance
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_sms_messages_date` ON `sms_messages` (`date`)")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_sms_messages_address` ON `sms_messages` (`address`)")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_sms_messages_type` ON `sms_messages` (`type`)")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_sms_messages_isSynced` ON `sms_messages` (`isSynced`)")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_sms_messages_threadId` ON `sms_messages` (`threadId`)")
        
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_remote_sms_messages_syncedAt` ON `remote_sms_messages` (`syncedAt`)")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_remote_sms_messages_date` ON `remote_sms_messages` (`date`)")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_remote_sms_messages_address` ON `remote_sms_messages` (`address`)")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_remote_sms_messages_originalSmsId` ON `remote_sms_messages` (`originalSmsId`)")
    }
}
