package com.triputranto.newsapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.data.entity.BaseResponse
import com.triputranto.newsapp.utils.NetworkState
import io.reactivex.Observable

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
interface AppRepository {

    //Remote
    fun getTopHeadlines(): LiveData<PagedList<Articles>>
    fun getSearchTopHeadlines(query: String): Observable<BaseResponse<Articles>>
    fun getNetworkState(): LiveData<NetworkState>

    //Local
    fun insertToDatabase(articles: Articles)
    fun getAllLocalData(): DataSource.Factory<Int, Articles>?
    fun getLocalDataByTitle(title: String): LiveData<Articles>
    fun deleteLocalDataByTitle(title: String)

}