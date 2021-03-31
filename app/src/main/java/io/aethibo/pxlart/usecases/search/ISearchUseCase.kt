package io.aethibo.pxlart.usecases.search

import androidx.paging.PagingData
import io.aethibo.pxlart.domain.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ISearchUseCase {
    fun getSearchResultStream(query: String, scope: CoroutineScope): Flow<PagingData<Photo>>
}