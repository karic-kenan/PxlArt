package io.aethibo.pxlart.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.aethibo.pxlart.data.remote.api.ApiService
import io.aethibo.pxlart.domain.Photo
import io.aethibo.pxlart.framework.utils.AppConst
import retrofit2.HttpException
import java.io.IOException

class SearchPhotosPagingSource(private val apiService: ApiService, private val query: String) :
    PagingSource<Int, Photo>() {

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val position = params.key ?: AppConst.pexelsStartingIndexPage
            val response = apiService.searchPhotos(position, params.loadSize, query)

            LoadResult.Page(
                data = response.photos,
                prevKey = if (position == AppConst.pexelsStartingIndexPage) null else position - 1,
                nextKey = if (response.photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}