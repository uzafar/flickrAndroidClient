package com.example.flickrandroidclient.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.flickrandroidclient.R
import com.example.flickrandroidclient.model.Photo

class FlickrPhotoPagingAdapter(private val navController: NavController) : PagingDataAdapter<Photo, FlickrPhotoViewHolder>(
    FlickrDataDiffCallBack()
) {
    override fun onBindViewHolder(holder: FlickrPhotoViewHolder, position: Int) {
        getItem(position)?.let { photo ->  holder.onBind(photo, navController) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrPhotoViewHolder {
        return FlickrPhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.photo_view, parent, false))
    }
}

class FlickrDataDiffCallBack : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}