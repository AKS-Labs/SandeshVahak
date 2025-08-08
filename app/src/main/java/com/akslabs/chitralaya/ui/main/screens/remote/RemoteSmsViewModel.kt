package com.akslabs.chitralaya.ui.main.screens.remote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.akslabs.SandeshVahak.data.localdb.DbHolder
import com.akslabs.chitralaya.data.localdb.entities.RemoteSmsMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RemoteSmsViewModel : ViewModel() {

    private val remoteSmsDao = DbHolder.database.remoteSmsMessageDao()

    /**
     * Paging flow for all synced SMS messages (ordered by sync date)
     */
    val allSyncedSmsFlow: Flow<PagingData<RemoteSmsMessage>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 5
        ),
        pagingSourceFactory = { remoteSmsDao.getAllPaging() }
    ).flow.cachedIn(viewModelScope)

    /**
     * Paging flow for all synced SMS messages (ordered by original message date)
     */
    val allSyncedSmsByDateFlow: Flow<PagingData<RemoteSmsMessage>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 5
        ),
        pagingSourceFactory = { remoteSmsDao.getAllByOriginalDatePaging() }
    ).flow.cachedIn(viewModelScope)

    /**
     * Flow for total count of synced SMS messages
     */
    val totalSyncedSmsCount: Flow<Int> = remoteSmsDao.getTotalCountFlow()
}
