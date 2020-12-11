package io.aethibo.pxlart.core.data.remote.api

import io.aethibo.pxlart.BuildConfig
import io.aethibo.pxlart.core.entities.MainResponse
import io.aethibo.pxlart.core.utils.AppConst
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Service to fetch Pexels posts using end point [AppConst.baseUrl].
 */
interface ApiService {

    /**
     * @param [page] by API is defaulted to 1, so it can be omitted
     * @param [per_page] by API is defaulted to 15, so it can be omitted
     *
     * We kept them here for clarity.
     */

    @Headers("Authorization: ${BuildConfig.PEXELS_API_KEY}")
    @GET(AppConst.searchPhotos)
    suspend fun searchPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("query") query: String
    ): MainResponse

    @Headers("Authorization: ${BuildConfig.PEXELS_API_KEY}")
    @GET(AppConst.curatedPhotos)
    suspend fun getCuratedPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): MainResponse
}