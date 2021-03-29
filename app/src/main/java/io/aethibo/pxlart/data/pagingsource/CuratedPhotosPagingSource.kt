package io.aethibo.pxlart.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.aethibo.pxlart.data.remote.api.ApiService
import io.aethibo.pxlart.domain.Photo
import io.aethibo.pxlart.framework.utils.AppConst
import retrofit2.HttpException
import java.io.IOException

class CuratedPhotosPagingSource(private val service: ApiService) : PagingSource<Int, Photo>() {

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
            val requestMap = mapOf("page" to position, "per_page" to params.loadSize)
            val response = service.getCuratedPhotos(requestMap)

            LoadResult.Page(
                    data = response.photos,
                    prevKey = if (position == AppConst.pexelsStartingIndexPage) null else position - 1, // if prevKey = null, paging is only forward
                    nextKey = if (response.photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}