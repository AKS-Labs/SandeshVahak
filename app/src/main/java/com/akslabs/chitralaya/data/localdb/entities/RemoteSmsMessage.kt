package com.akslabs.SandeshVahak.data.localdb.entities

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@Entity(tableName = "remote_sms_messages")
data class RemoteSmsMessage(
    @PrimaryKey val remoteId: String,              // Telegram message ID
    @ColumnInfo val originalSmsId: String,         // Original SMS ID from device
    @ColumnInfo val address: String,               // Phone number or contact
    @ColumnInfo val body: String,                  // Message content
    @ColumnInfo val date: Long,                    // Original SMS date
    @ColumnInfo val type: Int,                     // Message type (1=received, 2=sent)
    @ColumnInfo val syncedAt: Long = System.currentTimeMillis(), // When synced to Telegram
    @ColumnInfo val telegramChannelId: Long? = null, // Telegram channel ID where synced
    @ColumnInfo val telegramMessageId: Int? = null   // Telegram message ID in channel
) : Parcelable {

    companion object {
        @JvmStatic
        @JsonCreator
        fun create(
            @JsonProperty("remoteId") remoteId: String,
            @JsonProperty("originalSmsId") originalSmsId: String,
            @JsonProperty("address") address: String,
            @JsonProperty("body") body: String,
            @JsonProperty("date") date: Long,
            @JsonProperty("type") type: Int,
            @JsonProperty("syncedAt") syncedAt: Long = System.currentTimeMillis(),
            @JsonProperty("telegramChannelId") telegramChannelId: Long? = null,
            @JsonProperty("telegramMessageId") telegramMessageId: Int? = null
        ): RemoteSmsMessage = RemoteSmsMessage(
            remoteId, originalSmsId, address, body, date, type, syncedAt, telegramChannelId, telegramMessageId
        )
    }

    /**
     * Get display name for the message sender/recipient
     */
    fun getDisplayAddress(): String {
        return address.takeIf { it.isNotBlank() } ?: "Unknown"
    }

    /**
     * Get message type description
     */
    fun getTypeDescription(): String {
        return when (type) {
            SmsMessage.MESSAGE_TYPE_INBOX -> "Received"
            SmsMessage.MESSAGE_TYPE_SENT -> "Sent"
            SmsMessage.MESSAGE_TYPE_DRAFT -> "Draft"
            SmsMessage.MESSAGE_TYPE_OUTBOX -> "Outbox"
            SmsMessage.MESSAGE_TYPE_FAILED -> "Failed"
            SmsMessage.MESSAGE_TYPE_QUEUED -> "Queued"
            else -> "Unknown"
        }
    }

    /**
     * Check if this is a received message
     */
    fun isReceived(): Boolean = type == SmsMessage.MESSAGE_TYPE_INBOX

    /**
     * Check if this is a sent message
     */
    fun isSent(): Boolean = type == SmsMessage.MESSAGE_TYPE_SENT

    /**
     * Convert back to SmsMessage (for compatibility)
     */
    fun toSmsMessage(): SmsMessage {
        return SmsMessage(
            id = originalSmsId,
            threadId = "", // Not available in remote
            address = address,
            person = null,
            date = date,
            dateSent = null,
            protocol = null,
            read = true, // Assume read since it's synced
            status = -1,
            type = type,
            replyPathPresent = false,
            subject = null,
            body = body,
            serviceCenter = null,
            locked = false,
            errorCode = 0,
            seen = true,
            remoteId = remoteId,
            isSynced = true,
            syncedAt = syncedAt,
            syncAttempts = 0,
            lastSyncAttempt = null,
            syncError = null
        )
    }

    /**
     * Format message for display
     */
    fun formatForDisplay(): String {
        val direction = if (isReceived()) "📨" else "📤"
        val timestamp = java.text.SimpleDateFormat("MMM dd, HH:mm", java.util.Locale.getDefault())
            .format(java.util.Date(date))
        
        return "$direction ${getDisplayAddress()} • $timestamp"
    }
}
