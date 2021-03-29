package io.aethibo.pxlart.data.remote.api

import io.aethibo.pxlart.domain.MainResponse
import io.aethibo.pxlart.framework.utils.AppConst
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Service to fetch Pexels posts using end point [AppConst.baseUrl].
 */
interface ApiService {

    /**
     * @param [page] by API is defaulted to 1, so it can be omitted
     * @param [per_page] by API is defaulted to 15, so it can be omitted
     *
     * We kept them here for clarity.
     *
     * As you can see we have two different query handlings for our API calls.
     * Which you use is totally up to you since either does the job.
     * @param Query is only taking single parameter, this is in case you want to clearly
     * see within your calls what is each of the parameters passed in
     *
     * @param QueryMap as the name says will take a map of items which is defined elsewhere.
     * QueryMaps are quite good if you have a lot of calls that take several query
     * parameters, so the code is bit more concise.
     *
     * In our example app, we'll use both so you can see their usage.
     */

    @GET(AppConst.searchPhotos)
    suspend fun searchPhotos(
            @Query("page") page: Int,
            @Query("per_page") perPage: Int,
            @Query("query") query: String
    ): MainResponse

    @GET(AppConst.curatedPhotos)
    suspend fun getCuratedPhotos(@QueryMap params: Map<String, Int>): MainResponse
}