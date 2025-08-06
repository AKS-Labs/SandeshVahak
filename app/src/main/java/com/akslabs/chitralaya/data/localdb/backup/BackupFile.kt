package com.akslabs.Suchak.data.localdb.backup

import androidx.annotation.Keep
import com.akslabs.Suchak.data.localdb.entities.Photo
import com.akslabs.Suchak.data.localdb.entities.RemotePhoto
import com.akslabs.chitralaya.data.localdb.entities.SmsMessage
import com.akslabs.chitralaya.data.localdb.entities.RemoteSmsMessage

@Keep
data class BackupFile(
    val photos: List<Photo> = emptyList(),
    val remotePhotos: List<RemotePhoto> = emptyList(),
    val smsMessages: List<SmsMessage> = emptyList(),
    val remoteSmsMessages: List<RemoteSmsMessage> = emptyList(),
    val backupVersion: Int = 2, // Version 2 includes SMS support
    val backupTimestamp: Long = System.currentTimeMillis()
)
