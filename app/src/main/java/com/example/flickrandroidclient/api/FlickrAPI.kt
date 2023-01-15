package com.example.flickrandroidclient.api

import com.example.flickrandroidclient.model.FlickrData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrAPI {
    // Replace API_KEY with the key you got from Flickr
    @GET("/services/rest/?method=flickr.photos.search&api_key=[API_KEY]&format=json&nojsoncallback=1")
    suspend fun getFlickrData(@Query("text") text: String, @Query("page") page: String) : Response<FlickrData>
}