package io.aethibo.pxlart.ui.shared.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.aethibo.pxlart.domain.Photo

/**
 * Adapter for the list of all photos
 */
class SearchAdapter : ListAdapter<Photo, PhotosViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem.hashCode() == newItem.hashCode()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder =
            PhotosViewHolder.create(parent)

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val photo = getItem(position) ?: return
        holder.bind(photo)
    }
}