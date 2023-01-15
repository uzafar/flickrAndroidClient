package com.example.flickrandroidclient.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.flickrandroidclient.api.FlickrAPI
import com.example.flickrandroidclient.model.Photo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlickrPagingSource constructor(private val searchText: String) : PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition -> state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        if (searchText == "") {
            return LoadResult.Error(Throwable())
        }
        val page: Int = params.key ?: 1
        val baseUrl = "https://www.flickr.com/"
        val query
                = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build().create(FlickrAPI::class.java)

        val photos = query.getFlickrData(searchText, page.toString()).body()?.photos?.photo ?: return LoadResult.Page(data = listOf(), null, page)
        return LoadResult.Page(data = photos, prevKey = null, nextKey = page + 1)
    }
}