package com.akslabs.chitralaya.data.localdb.dao

import androidx.annotation.Keep
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.akslabs.chitralaya.data.localdb.entities.RemoteSmsMessage
import kotlinx.coroutines.flow.Flow

@Keep
@Dao
interface RemoteSmsMessageDao {

    @Query("SELECT * FROM remote_sms_messages ORDER BY syncedAt DESC")
    suspend fun getAll(): List<RemoteSmsMessage>

    @Query("SELECT * FROM remote_sms_messages ORDER BY syncedAt DESC")
    fun getAllPaging(): PagingSource<Int, RemoteSmsMessage>

    @Query("SELECT * FROM remote_sms_messages ORDER BY date DESC")
    fun getAllByOriginalDatePaging(): PagingSource<Int, RemoteSmsMessage>

    @Query("SELECT COUNT(*) FROM remote_sms_messages")
    fun getTotalCountFlow(): Flow<Int>

    @Query("SELECT * FROM remote_sms_messages WHERE remoteId = :remoteId")
    suspend fun getByRemoteId(remoteId: String): RemoteSmsMessage?

    @Query("SELECT * FROM remote_sms_messages WHERE originalSmsId = :originalSmsId")
    suspend fun getByOriginalSmsId(originalSmsId: String): RemoteSmsMessage?

    @Query("SELECT EXISTS(SELECT 1 FROM remote_sms_messages WHERE remoteId = :remoteId)")
    suspend fun existsByRemoteId(remoteId: String): Boolean

    @Query("SELECT EXISTS(SELECT 1 FROM remote_sms_messages WHERE originalSmsId = :originalSmsId)")
    suspend fun existsByOriginalSmsId(originalSmsId: String): Boolean

    @Query("SELECT * FROM remote_sms_messages WHERE address = :address ORDER BY date DESC")
    suspend fun getMessagesByAddress(address: String): List<RemoteSmsMessage>

    @Query("SELECT * FROM remote_sms_messages WHERE type = :type ORDER BY date DESC")
    suspend fun getMessagesByType(type: Int): List<RemoteSmsMessage>

    @Query("SELECT * FROM remote_sms_messages WHERE date >= :startDate AND date <= :endDate ORDER BY date DESC")
    suspend fun getMessagesByDateRange(startDate: Long, endDate: Long): List<RemoteSmsMessage>

    @Query("SELECT * FROM remote_sms_messages WHERE syncedAt >= :timestamp ORDER BY syncedAt DESC")
    suspend fun getMessagesSyncedAfter(timestamp: Long): List<RemoteSmsMessage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg remoteMessages: RemoteSmsMessage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteMessage: RemoteSmsMessage)

    @Update
    suspend fun update(remoteMessage: RemoteSmsMessage)

    @Delete
    suspend fun deleteAll(vararg remoteMessages: RemoteSmsMessage)

    @Query("DELETE FROM remote_sms_messages WHERE remoteId = :remoteId")
    suspend fun deleteByRemoteId(remoteId: String)

    @Query("DELETE FROM remote_sms_messages WHERE originalSmsId = :originalSmsId")
    suspend fun deleteByOriginalSmsId(originalSmsId: String)

    @Query("DELETE FROM remote_sms_messages")
    suspend fun deleteAll()

    @Query("DELETE FROM remote_sms_messages WHERE syncedAt < :timestamp")
    suspend fun deleteOlderThan(timestamp: Long)

    @Query("SELECT MAX(syncedAt) FROM remote_sms_messages")
    suspend fun getLatestSyncDate(): Long?

    @Query("SELECT MIN(syncedAt) FROM remote_sms_messages")
    suspend fun getOldestSyncDate(): Long?

    @Query("SELECT DISTINCT address FROM remote_sms_messages ORDER BY address")
    suspend fun getAllAddresses(): List<String>

    @Query("SELECT DISTINCT telegramChannelId FROM remote_sms_messages WHERE telegramChannelId IS NOT NULL")
    suspend fun getAllChannelIds(): List<Long>

    // Statistics queries
    @Query("SELECT COUNT(*) FROM remote_sms_messages WHERE type = 1") // Received
    suspend fun getReceivedCount(): Int

    @Query("SELECT COUNT(*) FROM remote_sms_messages WHERE type = 2") // Sent
    suspend fun getSentCount(): Int

    @Query("SELECT COUNT(*) FROM remote_sms_messages WHERE syncedAt >= :timestamp")
    suspend fun getSyncedCountSince(timestamp: Long): Int

    @Query("SELECT COUNT(*) FROM remote_sms_messages WHERE date >= :startDate AND date <= :endDate")
    suspend fun getMessageCountInDateRange(startDate: Long, endDate: Long): Int
}
