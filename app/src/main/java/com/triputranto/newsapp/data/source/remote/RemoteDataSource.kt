package com.triputranto.newsapp.data.source.remote

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class RemoteDataSource(private val apiService: ApiService) {

    fun getTopHeadlines(country: String) = apiService.getTopHeadlines(country)

}