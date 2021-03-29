package io.aethibo.pxlart.ui.curated.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import io.aethibo.pxlart.domain.Photo
import io.aethibo.pxlart.usecases.curated.ICuratedUseCase
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel for the [CuratedPhotosFragment] screen.
 * The ViewModel works with the [CuratedPhotosRepository] to get the data.
 */
class CuratedViewModel(private val useCase: ICuratedUseCase) : ViewModel() {

    /*
     * Get curated photos
     */
    fun getCuratedPhotos(): Flow<PagingData<Photo>> = useCase.getCuratedPhotos(viewModelScope)
}