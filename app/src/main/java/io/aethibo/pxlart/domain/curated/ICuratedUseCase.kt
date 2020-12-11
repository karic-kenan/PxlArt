package io.aethibo.pxlart.domain.curated

import androidx.paging.PagingData
import io.aethibo.pxlart.core.entities.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ICuratedUseCase {
    fun getCuratedPhotos(scope: CoroutineScope): Flow<PagingData<Photo>>
}