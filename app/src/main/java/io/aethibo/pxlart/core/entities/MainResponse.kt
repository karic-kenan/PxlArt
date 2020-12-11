package io.aethibo.pxlart.core.entities

/**
 * Data class to hold repo responses from searchPhotos API calls.
 */
data class MainResponse(
    val next_page: String = "",
    val page: Int = 0,
    val per_page: Int = 0,
    val photos: List<Photo> = emptyList(),
    val total_results: Int = 0
)