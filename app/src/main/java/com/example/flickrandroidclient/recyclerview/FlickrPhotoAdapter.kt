package com.example.flickrandroidclient.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrandroidclient.R
import com.example.flickrandroidclient.model.FlickrData

class FlickrPhotoAdapter(private val flickrData: FlickrData?, private val navController: NavController) : RecyclerView.Adapter<FlickrPhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrPhotoViewHolder {
        return FlickrPhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.photo_view, parent, false))
    }

    override fun onBindViewHolder(holder: FlickrPhotoViewHolder, position: Int) {
        flickrData?.photos?.photo?.get(position)?.let { photo -> holder.onBind(photo, navController) }
    }

    override fun getItemCount(): Int {
        return flickrData?.photos?.photo?.size ?: 0
    }
}