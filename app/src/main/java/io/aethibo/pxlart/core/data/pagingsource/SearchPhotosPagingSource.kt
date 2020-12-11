package io.aethibo.pxlart.core.data.pagingsource

import androidx.paging.PagingSource
import io.aethibo.pxlart.core.data.remote.api.ApiService
import io.aethibo.pxlart.core.entities.Photo
import io.aethibo.pxlart.core.utils.AppConst
import retrofit2.HttpException
import java.io.IOException

class SearchPhotosPagingSource(private val apiService: ApiService, private val query: String) :
    PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: AppConst.pexelsStartingIndexPage

        return try {
            val response = apiService.searchPhotos(position, params.loadSize, query)
            val photos = response.photos

            LoadResult.Page(
                data = photos,
                prevKey = if (position == AppConst.pexelsStartingIndexPage) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}