package io.aethibo.pxlart.domain

import com.squareup.moshi.Json

/**
 * Data class to hold overall information about the photo.
 */
data class Photo(
    val height: Int = 0,
    val id: Int = 0,
    val liked: Boolean = false,
    val photographer: String = "",
    @Json(name = "photographer_id") val photographerId: Int = 0,
    @Json(name = "photographer_url") val photographerUrl: String = "",
    val src: Src = Src(),
    val url: String = "",
    val width: Int = 0
)