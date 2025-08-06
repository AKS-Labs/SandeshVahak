package com.akslabs.Suchak.data.localdb.dao

import androidx.annotation.Keep
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akslabs.Suchak.data.localdb.entities.Photo
import com.akslabs.Suchak.data.localdb.entities.RemotePhoto
import kotlinx.coroutines.flow.Flow

@Keep
@Dao
interface RemotePhotoDao {

    @Query("SELECT * FROM remote_photos ORDER BY uploadedAt DESC")
    suspend fun getAll(): List<RemotePhoto>

    @Query("SELECT * FROM remote_photos ORDER BY uploadedAt DESC")
    fun getAllPaging(): PagingSource<Int, RemotePhoto>

    @Query("SELECT COUNT(*) FROM remote_photos")
    fun getTotalCountFlow(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg remotePhotos: RemotePhoto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remotePhoto: RemotePhoto)

    @Delete
    suspend fun deleteAll(vararg remotePhotos: RemotePhoto)

    @Query("DELETE FROM remote_photos WHERE remoteId = :remoteId")
    suspend fun deleteByRemoteId(remoteId: String)

    @Query("SELECT * FROM remote_photos WHERE remoteId = :remoteId")
    suspend fun getByRemoteId(remoteId: String): RemotePhoto?

    @Query("UPDATE remote_photos SET thumbnailCached = :cached WHERE remoteId = :remoteId")
    suspend fun updateThumbnailCached(remoteId: String, cached: Boolean)

    // For "delete backed up photos" feature - get photos that exist in cloud but can be deleted from device
    @Query(
        "SELECT p.* FROM photos p INNER JOIN remote_photos rp ON p.remoteId = rp.remoteId WHERE p.remoteId IS NOT NULL"
    )
    suspend fun getBackedUpPhotosOnDevice(): List<Photo>
}