package com.triputranto.newsapp.data.source.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.triputranto.newsapp.data.entity.Articles

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class RemoteDataSourceFactory(private val remoteDataSource: RemoteDataSource) : DataSource.Factory<Int, Articles>() {
    val remoteLiveDataSource = MutableLiveData<RemoteDataSource>()
    override fun create(): DataSource<Int, Articles> {
        remoteLiveDataSource.postValue(remoteDataSource)
        return remoteDataSource
    }

}