package io.aethibo.pxlart.usecases.curated

import androidx.paging.PagingData
import io.aethibo.pxlart.domain.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ICuratedUseCase {
    fun getCuratedPhotos(scope: CoroutineScope): Flow<PagingData<Photo>>
}