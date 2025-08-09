package com.akslabs.chitralaya.data.localdb.dao

import androidx.annotation.Keep
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.akslabs.chitralaya.data.localdb.entities.SmsMessage
import kotlinx.coroutines.flow.Flow

@Keep
@Dao
interface SmsMessageDao {

    @Query("SELECT * FROM sms_messages ORDER BY date DESC")
    suspend fun getAll(): List<SmsMessage>

    @Query("SELECT * FROM sms_messages ORDER BY date DESC")
    fun getAllPaging(): PagingSource<Int, SmsMessage>

    @Query("SELECT * FROM sms_messages WHERE isSynced = 0 ORDER BY date DESC")
    fun getUnsyncedPaging(): PagingSource<Int, SmsMessage>

    @Query("SELECT * FROM sms_messages WHERE isSynced = 1 ORDER BY syncedAt DESC")
    fun getSyncedPaging(): PagingSource<Int, SmsMessage>

    @Query("SELECT COUNT(*) FROM sms_messages")
    fun getAllCountFlow(): Flow<Int>

    @Query("SELECT COUNT(*) FROM sms_messages WHERE isSynced = 0")
    fun getUnsyncedCountFlow(): Flow<Int>

    @Query("SELECT COUNT(*) FROM sms_messages WHERE isSynced = 1")
    fun getSyncedCountFlow(): Flow<Int>

    @Query("SELECT * FROM sms_messages WHERE id = :id")
    suspend fun getById(id: String): SmsMessage?

    @Query("SELECT * FROM sms_messages WHERE remoteId = :remoteId")
    suspend fun getByRemoteId(remoteId: String): SmsMessage?

    @Query("SELECT EXISTS(SELECT 1 FROM sms_messages WHERE id = :id)")
    suspend fun exists(id: String): Boolean

    @Query("SELECT EXISTS(SELECT 1 FROM sms_messages WHERE id = :id AND isSynced = 1)")
    suspend fun isSynced(id: String): Boolean

    @Query("SELECT * FROM sms_messages WHERE isSynced = 0 AND syncAttempts < 3 ORDER BY date ASC LIMIT :limit")
    suspend fun getUnsyncedMessages(limit: Int = 50): List<SmsMessage>

    @Query("SELECT * FROM sms_messages WHERE isSynced = 0 AND syncAttempts < 3 ORDER BY date DESC LIMIT :limit")
    suspend fun getLatestUnsyncedMessages(limit: Int = 5): List<SmsMessage>

    /**
     * Get unsynced messages that are after the specified baseline timestamp (for NEW_ONLY mode)
     */
    @Query("SELECT * FROM sms_messages WHERE isSynced = 0 AND syncAttempts < 3 AND date >= :baselineTimestamp ORDER BY date ASC LIMIT :limit")
    suspend fun getUnsyncedMessagesAfterBaseline(baselineTimestamp: Long, limit: Int = 50): List<SmsMessage>

    /**
     * Get latest unsynced messages that are after the specified baseline timestamp (for NEW_ONLY mode)
     */
    @Query("SELECT * FROM sms_messages WHERE isSynced = 0 AND syncAttempts < 3 AND date >= :baselineTimestamp ORDER BY date DESC LIMIT :limit")
    suspend fun getLatestUnsyncedMessagesAfterBaseline(baselineTimestamp: Long, limit: Int = 5): List<SmsMessage>

    @Query("SELECT * FROM sms_messages WHERE isSynced = 0 AND id IN (:ids)")
    suspend fun getUnsyncedByIds(ids: List<String>): List<SmsMessage>

    @Query("SELECT * FROM sms_messages WHERE date > :timestamp ORDER BY date ASC")
    suspend fun getMessagesAfter(timestamp: Long): List<SmsMessage>

    @Query("SELECT * FROM sms_messages WHERE threadId = :threadId ORDER BY date ASC")
    suspend fun getMessagesByThread(threadId: String): List<SmsMessage>

    @Query("SELECT * FROM sms_messages WHERE address = :address ORDER BY date DESC")
    suspend fun getMessagesByAddress(address: String): List<SmsMessage>

    @Query("SELECT * FROM sms_messages WHERE type = :type ORDER BY date DESC")
    suspend fun getMessagesByType(type: Int): List<SmsMessage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(vararg messages: SmsMessage): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: SmsMessage): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg messages: SmsMessage)

    @Update
    suspend fun updateMessages(vararg messages: SmsMessage)

    @Update
    suspend fun update(message: SmsMessage)

    @Query("UPDATE sms_messages SET isSynced = 1, remoteId = :remoteId, syncedAt = :syncedAt WHERE id = :id")
    suspend fun markAsSynced(id: String, remoteId: String, syncedAt: Long = System.currentTimeMillis())

    @Query("UPDATE sms_messages SET syncAttempts = syncAttempts + 1, lastSyncAttempt = :timestamp, syncError = :error WHERE id = :id")
    suspend fun updateSyncAttempt(id: String, timestamp: Long = System.currentTimeMillis(), error: String? = null)

    @Query("UPDATE sms_messages SET syncAttempts = 0, lastSyncAttempt = NULL, syncError = NULL WHERE id = :id")
    suspend fun resetSyncAttempts(id: String)

    @Query("UPDATE sms_messages SET syncAttempts = syncAttempts + 1, lastSyncAttempt = :timestamp, syncError = :error WHERE id IN (:ids)")
    suspend fun batchUpdateSyncAttempts(ids: List<String>, timestamp: Long = System.currentTimeMillis(), error: String? = null)

    @Query("DELETE FROM sms_messages WHERE id = :id")
    suspend fun deleteById(id: String)

    @Delete
    suspend fun deleteMessages(vararg messages: SmsMessage)

    @Query("DELETE FROM sms_messages")
    suspend fun deleteAll()

    @Query("DELETE FROM sms_messages WHERE date < :timestamp")
    suspend fun deleteOlderThan(timestamp: Long)

    @Query("SELECT MAX(date) FROM sms_messages")
    suspend fun getLatestMessageDate(): Long?

    @Query("SELECT MIN(date) FROM sms_messages")
    suspend fun getOldestMessageDate(): Long?

    @Query("SELECT DISTINCT address FROM sms_messages ORDER BY address")
    suspend fun getAllAddresses(): List<String>

    @Query("SELECT DISTINCT threadId FROM sms_messages ORDER BY threadId")
    suspend fun getAllThreadIds(): List<String>

    // Statistics queries
    @Query("SELECT COUNT(*) FROM sms_messages WHERE type = 1") // Received
    suspend fun getReceivedCount(): Int

    @Query("SELECT COUNT(*) FROM sms_messages WHERE type = 2") // Sent
    suspend fun getSentCount(): Int

    @Query("SELECT COUNT(*) FROM sms_messages WHERE date >= :timestamp")
    suspend fun getMessageCountSince(timestamp: Long): Int
}
