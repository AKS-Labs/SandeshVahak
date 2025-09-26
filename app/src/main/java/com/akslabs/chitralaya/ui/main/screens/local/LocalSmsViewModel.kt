package com.akslabs.SandeshVahak.ui.main.screens.local

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.akslabs.SandeshVahak.data.localdb.DbHolder
import com.akslabs.SandeshVahak.data.localdb.entities.SmsMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LocalSmsViewModel : ViewModel() {

    private val smsDao = DbHolder.database.smsMessageDao()

    /**
     * Paging flow for all local SMS messages
     */
    val localSmsFlow: Flow<PagingData<SmsMessage>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 5
        ),
        pagingSourceFactory = { smsDao.getAllPaging() }
    ).flow.cachedIn(viewModelScope)

    /**
     * Flow for total count of local SMS messages
     */
    val localSmsCount: Flow<Int> = smsDao.getAllCountFlow()

    /**
     * Paging flow for unsynced SMS messages only
     */
    val unsyncedSmsFlow: Flow<PagingData<SmsMessage>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 5
        ),
        pagingSourceFactory = { smsDao.getUnsyncedPaging() }
    ).flow.cachedIn(viewModelScope)

    /**
     * Flow for count of unsynced SMS messages
     */
    val unsyncedSmsCount: Flow<Int> = smsDao.getUnsyncedCountFlow()

    /**
     * Paging flow for synced SMS messages only
     */
    val syncedSmsFlow: Flow<PagingData<SmsMessage>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 5
        ),
        pagingSourceFactory = { smsDao.getSyncedPaging() }
    ).flow.cachedIn(viewModelScope)

    /**
     * Flow for count of synced SMS messages
     */
    val syncedSmsCount: Flow<Int> = smsDao.getSyncedCountFlow()
}
