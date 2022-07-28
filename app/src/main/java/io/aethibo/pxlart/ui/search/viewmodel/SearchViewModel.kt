package io.aethibo.pxlart.ui.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import io.aethibo.pxlart.domain.Photo
import io.aethibo.pxlart.usecases.search.ISearchUseCase
import kotlinx.coroutines.flow.Flow

class SearchViewModel(private val useCase: ISearchUseCase) : ViewModel() {

    /**
     * Search for photos
     */
    fun getSearchResult(query: String): Flow<PagingData<Photo>> = useCase.getSearchResultStream(query, viewModelScope)
}