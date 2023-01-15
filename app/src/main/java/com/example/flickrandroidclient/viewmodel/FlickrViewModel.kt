package com.example.flickrandroidclient.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.flickrandroidclient.model.FlickrData
import com.example.flickrandroidclient.model.Photo
import com.example.flickrandroidclient.repository.FlickrRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlickrViewModel @Inject constructor(private val flickrRepository: FlickrRepository): ViewModel() {
    //cached
    private var photoFlowCached: MutableStateFlow<PagingData<Photo>> = MutableStateFlow(PagingData.empty())

    private val photoFlow: StateFlow<PagingData<Photo>> = photoFlowCached
    private var prevQuery: String = ""

    fun getFlickrData(searchText: String): LiveData<FlickrData> {
        return liveData {
            val response = flickrRepository.getFlickrData(searchText, "1")
            if (response.isSuccessful) {
                val data = response.body() ?: return@liveData
                emit(data)
            }
        }
    }

    fun getFlickrPagingData(searchText: String): Flow<PagingData<Photo>> {
        return if (searchText != prevQuery || searchText == "") {
            viewModelScope.launch {
                flickrRepository.getFlickrPagingData(searchText).cachedIn(viewModelScope).collect {
                    photoFlowCached.value = it
                    prevQuery = searchText
                }
            }
            photoFlow
        } else {
            photoFlow
        }
    }
}