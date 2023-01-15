package com.example.flickrandroidclient.recyclerview

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrandroidclient.R
import com.example.flickrandroidclient.model.Photo
import com.squareup.picasso.Picasso

class FlickrPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView

    init {
        imageView = itemView.findViewById(R.id.photo_image)
    }

    fun onBind(photo: Photo, navController: NavController) {
        val path = "https://live.staticflickr.com/"+photo.server+"/"+photo.id+"_"+photo.secret+".jpg"

        imageView.setOnClickListener {
            val args = Bundle()
            args.putString("URL", path)
            navController.navigate(R.id.PhotoDetailFragment, args)
        }
        Picasso.get().load(path).into(imageView)
    }
}