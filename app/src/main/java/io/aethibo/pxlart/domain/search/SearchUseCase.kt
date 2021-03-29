package io.aethibo.pxlart.domain.search

import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.aethibo.pxlart.data.repositories.MainRepository
import io.aethibo.pxlart.domain.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class SearchUseCase(private val repository: MainRepository) : ISearchUseCase {
    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<Photo>>? = null

    /**
     * Search photos based on query string
     */
    override fun getSearchResultStream(
        query: String,
        scope: CoroutineScope
    ): Flow<PagingData<Photo>> {
        val lastResult = currentSearchResult

        if (query == currentQueryValue && lastResult != null)
            return lastResult

        currentQueryValue = query

        val newResult: Flow<PagingData<Photo>> =
            repository.getSearchResultStream(query).cachedIn(scope)

        currentSearchResult = newResult

        return newResult
    }
}