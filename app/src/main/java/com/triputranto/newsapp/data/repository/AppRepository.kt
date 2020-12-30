package com.triputranto.newsapp.data.repository

import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.data.entity.BaseResponse
import io.reactivex.Observable

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
interface AppRepository {

    //fetch from remote
    fun getTopHeadlines(country: String): Observable<BaseResponse<Articles>>
}