package com.triputranto.newsapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.triputranto.newsapp.data.entity.Articles

/**
 * Created by Ahmad Tri Putranto on 01/01/2021.
 * */
class LocalDataSource(private val appDao: AppDao) {

    fun insertToDatabase(articles: Articles) = appDao.insertToLocalData(articles)
    fun getAllLocalData(): DataSource.Factory<Int, Articles> = appDao.getAllLocalData()
    fun getLocalDataByTitle(title: String): LiveData<Articles> = appDao.getLocalDataByTitle(title)
    fun deleteLocalDataByTitle(title: String) = appDao.deleteLocalDataByTitle(title)

}