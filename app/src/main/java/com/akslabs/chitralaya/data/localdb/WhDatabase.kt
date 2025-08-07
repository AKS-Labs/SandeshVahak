package com.akslabs.Suchak.data.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.akslabs.chitralaya.data.localdb.dao.SmsMessageDao
import com.akslabs.chitralaya.data.localdb.dao.RemoteSmsMessageDao
import com.akslabs.chitralaya.data.localdb.entities.SmsMessage
import com.akslabs.chitralaya.data.localdb.entities.RemoteSmsMessage
import com.akslabs.chitralaya.data.localdb.migration.Migration4to5_AddSmsSupport
import com.akslabs.chitralaya.data.localdb.migration.Migration5to6_RemovePhotoTables
import com.akslabs.chitralaya.data.localdb.migration.Migration6to7_FixSchemaIntegrity

@Database(
    entities = [
        SmsMessage::class, RemoteSmsMessage::class
    ],
    version = 7
)
abstract class WhDatabase : RoomDatabase() {
    abstract fun smsMessageDao(): SmsMessageDao
    abstract fun remoteSmsMessageDao(): RemoteSmsMessageDao

    companion object {
        private const val DATABASE_NAME = "TeledriveDb"

        private fun migrations() = arrayOf(
            Migration4to5_AddSmsSupport(),
            Migration5to6_RemovePhotoTables(),
            Migration6to7_FixSchemaIntegrity()
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