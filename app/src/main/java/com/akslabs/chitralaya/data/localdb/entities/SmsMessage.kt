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
@Entity(tableName = "sms_messages")
data class SmsMessage(
    @PrimaryKey val id: String,                    // SMS ID from content provider
    @ColumnInfo val threadId: String,              // SMS thread ID
    @ColumnInfo val address: String,               // Phone number or contact
    @ColumnInfo val person: String? = null,        // Contact person ID
    @ColumnInfo val date: Long,                    // Date received/sent (timestamp)
    @ColumnInfo val dateSent: Long? = null,        // Date sent (for sent messages)
    @ColumnInfo val protocol: String? = null,      // SMS protocol
    @ColumnInfo val read: Boolean = false,         // Read status
    @ColumnInfo val status: Int = -1,              // Message status
    @ColumnInfo val type: Int,                     // Message type (1=received, 2=sent, etc.)
    @ColumnInfo val replyPathPresent: Boolean = false, // Reply path present
    @ColumnInfo val subject: String? = null,       // Message subject (for MMS)
    @ColumnInfo val body: String,                  // Message content
    @ColumnInfo val serviceCenter: String? = null, // Service center
    @ColumnInfo val locked: Boolean = false,       // Locked status
    @ColumnInfo val errorCode: Int = 0,           // Error code
    @ColumnInfo val seen: Boolean = false,         // Seen status
    @ColumnInfo val remoteId: String? = null,      // Telegram message ID after sync
    @ColumnInfo val isSynced: Boolean = false,     // Whether synced to Telegram
    @ColumnInfo val syncedAt: Long? = null,        // When synced to Telegram
    @ColumnInfo val syncAttempts: Int = 0,         // Number of sync attempts
    @ColumnInfo val lastSyncAttempt: Long? = null, // Last sync attempt timestamp
    @ColumnInfo val syncError: String? = null      // Last sync error message
) : Parcelable {

    companion object {
        @JvmStatic
        @JsonCreator
        fun create(
            @JsonProperty("id") id: String,
            @JsonProperty("threadId") threadId: String,
            @JsonProperty("address") address: String,
            @JsonProperty("person") person: String? = null,
            @JsonProperty("date") date: Long,
            @JsonProperty("dateSent") dateSent: Long? = null,
            @JsonProperty("protocol") protocol: String? = null,
            @JsonProperty("read") read: Boolean = false,
            @JsonProperty("status") status: Int = -1,
            @JsonProperty("type") type: Int,
            @JsonProperty("replyPathPresent") replyPathPresent: Boolean = false,
            @JsonProperty("subject") subject: String? = null,
            @JsonProperty("body") body: String,
            @JsonProperty("serviceCenter") serviceCenter: String? = null,
            @JsonProperty("locked") locked: Boolean = false,
            @JsonProperty("errorCode") errorCode: Int = 0,
            @JsonProperty("seen") seen: Boolean = false,
            @JsonProperty("remoteId") remoteId: String? = null,
            @JsonProperty("isSynced") isSynced: Boolean = false,
            @JsonProperty("syncedAt") syncedAt: Long? = null,
            @JsonProperty("syncAttempts") syncAttempts: Int = 0,
            @JsonProperty("lastSyncAttempt") lastSyncAttempt: Long? = null,
            @JsonProperty("syncError") syncError: String? = null
        ): SmsMessage = SmsMessage(
            id, threadId, address, person, date, dateSent, protocol, read, status, type,
            replyPathPresent, subject, body, serviceCenter, locked, errorCode, seen,
            remoteId, isSynced, syncedAt, syncAttempts, lastSyncAttempt, syncError
        )

        // SMS message types
        const val MESSAGE_TYPE_ALL = 0
        const val MESSAGE_TYPE_INBOX = 1
        const val MESSAGE_TYPE_SENT = 2
        const val MESSAGE_TYPE_DRAFT = 3
        const val MESSAGE_TYPE_OUTBOX = 4
        const val MESSAGE_TYPE_FAILED = 5
        const val MESSAGE_TYPE_QUEUED = 6
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
            MESSAGE_TYPE_INBOX -> "Received"
            MESSAGE_TYPE_SENT -> "Sent"
            MESSAGE_TYPE_DRAFT -> "Draft"
            MESSAGE_TYPE_OUTBOX -> "Outbox"
            MESSAGE_TYPE_FAILED -> "Failed"
            MESSAGE_TYPE_QUEUED -> "Queued"
            else -> "Unknown"
        }
    }

    /**
     * Check if this is a received message
     */
    fun isReceived(): Boolean = type == MESSAGE_TYPE_INBOX

    /**
     * Check if this is a sent message
     */
    fun isSent(): Boolean = type == MESSAGE_TYPE_SENT

    /**
     * Convert to RemoteSmsMessage for synced messages
     */
    fun toRemoteSmsMessage(): RemoteSmsMessage? {
        return if (isSynced && remoteId != null) {
            RemoteSmsMessage(
                remoteId = remoteId,
                originalSmsId = id,
                address = address,
                body = body,
                date = date,
                type = type,
                syncedAt = syncedAt ?: System.currentTimeMillis()
            )
        } else null
    }

    /**
     * Format message for Telegram
     */
    fun formatForTelegram(): String {
        val direction = if (isReceived()) "📨 Received" else "📤 Sent"
        val timestamp = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault())
            .format(java.util.Date(date))
        
        return """
            $direction SMS
            From/To: ${getDisplayAddress()}
            Time: $timestamp
            
            Message:
            $body
        """.trimIndent()
    }
}
