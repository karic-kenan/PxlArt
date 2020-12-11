package io.aethibo.pxlart.core.entities

/**
 * Data class to hold overall information about the photo.
 */
data class Photo(
    val height: Int = 0,
    val id: Int = 0,
    val liked: Boolean = false,
    val photographer: String = "",
    val photographer_id: Int = 0,
    val photographer_url: String = "",
    val src: Src = Src(),
    val url: String = "",
    val width: Int = 0
)