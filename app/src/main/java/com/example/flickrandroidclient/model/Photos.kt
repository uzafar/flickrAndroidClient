package com.example.flickrandroidclient.model

import com.google.gson.annotations.SerializedName

data class Photos (
    @SerializedName("page"    ) var page    : Int?             = null,
    @SerializedName("pages"   ) var pages   : String?          = null,
    @SerializedName("perpage" ) var perpage : Int?             = null,
    @SerializedName("total"   ) var total   : String?          = null,
    @SerializedName("photo"   ) var photo   : ArrayList<Photo> = arrayListOf()
)
