package com.triputranto.newsapp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
data class BaseResponse<T>(
    @SerializedName("articles") val list: List<T>,
    @SerializedName("status") val status: String
)