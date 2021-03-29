package io.aethibo.pxlart.domain

import com.squareup.moshi.Json

/**
 * Data class to hold repo responses from searchPhotos API calls.
 */
data class MainResponse(
        @Json(name = "next_page") val nextPage: String = "",
        val page: Int = 0,
        @Json(name = "per_page") val perPage: Int = 0,
        val photos: List<Photo> = emptyList(),
        @Json(name = "total_results") val totalResults: Int = 0
)