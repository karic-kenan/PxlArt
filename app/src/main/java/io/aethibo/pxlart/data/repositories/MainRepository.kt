package io.aethibo.pxlart.data.repositories

import androidx.paging.PagingData
import io.aethibo.pxlart.domain.Photo
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getCuratedPhotos(): Flow<PagingData<Photo>>
    fun getSearchResultStream(query: String): Flow<PagingData<Photo>>
}