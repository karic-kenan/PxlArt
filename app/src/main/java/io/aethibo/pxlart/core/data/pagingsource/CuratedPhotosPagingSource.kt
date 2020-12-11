package io.aethibo.pxlart.core.data.pagingsource

import androidx.paging.PagingSource
import io.aethibo.pxlart.core.data.remote.api.ApiService
import io.aethibo.pxlart.core.entities.Photo
import io.aethibo.pxlart.core.utils.AppConst
import retrofit2.HttpException
import java.io.IOException

class CuratedPhotosPagingSource(private val service: ApiService) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: AppConst.pexelsStartingIndexPage

        return try {
            val response = service.getCuratedPhotos(position, params.loadSize)
            val curatedPhotos = response.photos

            LoadResult.Page(
                data = curatedPhotos,
                prevKey = if (position == AppConst.pexelsStartingIndexPage) null else position - 1,
                nextKey = if (curatedPhotos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}