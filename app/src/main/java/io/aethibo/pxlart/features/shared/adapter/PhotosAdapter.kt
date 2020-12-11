package io.aethibo.pxlart.features.shared.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.aethibo.pxlart.core.entities.Photo

/**
 * Adapter for the list of curated photos.
 */
class PhotosAdapter : PagingDataAdapter<Photo, RecyclerView.ViewHolder>(PHOTOS_COMPARATOR) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val photoItem = getItem(position)
        if (photoItem != null)
            (holder as PhotosViewHolder).bind(photoItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PhotosViewHolder.create(parent)
    }

    companion object {
        private val PHOTOS_COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem == newItem
        }
    }
}