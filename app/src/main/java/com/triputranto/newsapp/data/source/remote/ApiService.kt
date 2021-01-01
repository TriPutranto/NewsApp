package com.triputranto.newsapp.data.source.remote

import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.data.entity.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
interface ApiService {

    @GET("top-headlines/")
    fun getTopHeadlines(
            @Query("country") country: String? = null,
            @Query("page") page: Int?= null,
            @Query("pageSize") pageSize: Int? = null,
            @Query("q") query: String? = null
    ): Observable<BaseResponse<Articles>>

}