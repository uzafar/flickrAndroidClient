package com.example.flickrandroidclient.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.flickrandroidclient.api.FlickrAPI
import com.example.flickrandroidclient.model.FlickrData
import com.example.flickrandroidclient.model.Photo
import com.example.flickrandroidclient.paging.FlickrPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class FlickrRepository @Inject constructor() {
    suspend fun getFlickrData(searchText: String, page: String): Response<FlickrData> {
            val baseUrl = "https://www.flickr.com/"
            val query
                    = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                // we need to add converter factory to
                // convert JSON object to Java object
                .build().create(FlickrAPI::class.java)
            return query.getFlickrData(searchText, page)
    }

    fun getFlickrPagingData(searchText: String): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1
            ),
            pagingSourceFactory = {
                FlickrPagingSource(searchText)
            }
        ).flow
    }
}