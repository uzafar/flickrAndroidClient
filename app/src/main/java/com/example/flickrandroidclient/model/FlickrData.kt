package com.example.flickrandroidclient.model

import com.google.gson.annotations.SerializedName

data class FlickrData (
    @SerializedName("photos" ) var photos : Photos? = Photos(),
    @SerializedName("stat"   ) var stat   : String? = null
)