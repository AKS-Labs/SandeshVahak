package com.akslabs.chitralaya.data.localdb.backup

import androidx.annotation.Keep
import com.akslabs.chitralaya.data.localdb.entities.SmsMessage
import com.akslabs.chitralaya.data.localdb.entities.RemoteSmsMessage

@Keep
data class BackupFile(
    val smsMessages: List<SmsMessage> = emptyList(),
    val remoteSmsMessages: List<RemoteSmsMessage> = emptyList(),
    val backupVersion: Int = 3, // Version 3 is SMS-only
    val backupTimestamp: Long = System.currentTimeMillis()
)
