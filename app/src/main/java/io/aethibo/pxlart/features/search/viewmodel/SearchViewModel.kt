package io.aethibo.pxlart.features.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.aethibo.pxlart.domain.search.ISearchUseCase

class SearchViewModel(private val useCase: ISearchUseCase) : ViewModel() {

    /**
     * Search for photos
     */
    fun getSearchResult(query: String) = useCase.getSearchResultStream(query, viewModelScope)
}