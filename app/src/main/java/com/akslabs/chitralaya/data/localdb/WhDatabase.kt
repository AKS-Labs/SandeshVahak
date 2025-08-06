package com.akslabs.Suchak.data.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.akslabs.chitralaya.data.localdb.dao.SmsMessageDao
import com.akslabs.chitralaya.data.localdb.dao.RemoteSmsMessageDao
import com.akslabs.chitralaya.data.localdb.entities.SmsMessage
import com.akslabs.chitralaya.data.localdb.entities.RemoteSmsMessage
import com.akslabs.Suchak.data.localdb.migration.Migration1to2_NullableRemoteId
import com.akslabs.Suchak.data.localdb.migration.Migration2to3_RemotePhotoTable
import com.akslabs.Suchak.data.localdb.migration.Migration3to4_EnhancedRemotePhoto
import com.akslabs.chitralaya.data.localdb.migration.Migration4to5_AddSmsSupport

@Database(
    entities = [
        SmsMessage::class, RemoteSmsMessage::class
    ],
    version = 5
)
abstract class WhDatabase : RoomDatabase() {
    abstract fun smsMessageDao(): SmsMessageDao
    abstract fun remoteSmsMessageDao(): RemoteSmsMessageDao

    companion object {
        private const val DATABASE_NAME = "TeledriveDb"

        private fun migrations() = arrayOf(
            Migration1to2_NullableRemoteId(),
            Migration2to3_RemotePhotoTable(),
            Migration3to4_EnhancedRemotePhoto(),
            Migration4to5_AddSmsSupport()
        )

        fun create(applicationContext: Context): WhDatabase {
            return Room
                .databaseBuilder(
                    applicationContext,
                    WhDatabase::class.java,
                    DATABASE_NAME
                )
                .addMigrations(*migrations())
                .build()
        }
    }
}