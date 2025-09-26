package com.akslabs.SandeshVahak.data.localdb.backup

import androidx.annotation.Keep
import com.akslabs.SandeshVahak.data.localdb.entities.SmsMessage
import com.akslabs.SandeshVahak.data.localdb.entities.RemoteSmsMessage

@Keep
data class BackupFile(
    val smsMessages: List<SmsMessage> = emptyList(),
    val remoteSmsMessages: List<RemoteSmsMessage> = emptyList(),
    val backupVersion: Int = 3, // Version 3 is SMS-only
    val backupTimestamp: Long = System.currentTimeMillis()
)
