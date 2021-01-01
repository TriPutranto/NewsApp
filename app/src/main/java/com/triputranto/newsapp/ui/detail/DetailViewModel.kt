package com.triputranto.newsapp.ui.detail

import androidx.lifecycle.MutableLiveData
import com.triputranto.newsapp.base.BaseViewModel
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.data.repository.AppRepository

/**
 * Created by Ahmad Tri Putranto on 01/01/2021.
 * */
class DetailViewModel(private val appRepository: AppRepository) : BaseViewModel() {

    fun getLocalData(title: String) = appRepository.getLocalDataByTitle(title)

    fun addToFavorite(articles: Articles) {
        appRepository.insertToDatabase(articles)
    }

    fun removeFavorite(title: String) {
        appRepository.deleteLocalDataByTitle(title)
    }
}