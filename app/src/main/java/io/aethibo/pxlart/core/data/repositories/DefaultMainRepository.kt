package io.aethibo.pxlart.core.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import io.aethibo.pxlart.core.data.pagingsource.CuratedPhotosPagingSource
import io.aethibo.pxlart.core.data.pagingsource.SearchPhotosPagingSource
import io.aethibo.pxlart.core.data.remote.api.ApiService
import io.aethibo.pxlart.core.entities.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class DefaultMainRepository(private val apiService: ApiService) : MainRepository {

    override fun getCuratedPhotos(): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                CuratedPhotosPagingSource(service = apiService)
            }
        ).flow.flowOn(Dispatchers.IO)
    }

    /**
     * Search photos whose names match the query, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */
    override fun getSearchResultStream(query: String): Flow<PagingData<Photo>> {
        return Pager(config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ), pagingSourceFactory = {
            SearchPhotosPagingSource(apiService, query)
        }).flow.flowOn(Dispatchers.IO)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}