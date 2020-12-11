package io.aethibo.pxlart.features.shared.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.aethibo.pxlart.R
import io.aethibo.pxlart.core.entities.Photo

/**
 * View Holder for a [Photo] RecyclerView list item.
 */
class PhotosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.photoImage)

    private var photo: Photo? = null

    init {
        view.setOnClickListener {
            photo?.url.let {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                view.context.startActivity(intent)
            }
        }
    }

    fun bind(photo: Photo?) {
        if (photo == null) {
            image.visibility = View.GONE
        } else {
            showCuratedPhotos(photo)
        }
    }

    private fun showCuratedPhotos(photo: Photo) {
        this.photo = photo
        image.load(photo.src.portrait) {
            crossfade(true)
        }
    }

    companion object {
        fun create(parent: ViewGroup): PhotosViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo, parent, false)
            return PhotosViewHolder(view)
        }
    }
}