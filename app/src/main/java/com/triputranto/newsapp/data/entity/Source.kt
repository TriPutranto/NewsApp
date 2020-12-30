package com.triputranto.newsapp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
data class Source(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null
)