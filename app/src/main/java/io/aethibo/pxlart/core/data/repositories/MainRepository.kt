package io.aethibo.pxlart.core.data.repositories

import androidx.paging.PagingData
import io.aethibo.pxlart.core.entities.Photo
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getCuratedPhotos(): Flow<PagingData<Photo>>
    fun getSearchResultStream(query: String): Flow<PagingData<Photo>>
}