package com.triputranto.newsapp.ui.main.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.data.repository.AppRepository

/**
 * Created by Ahmad Tri Putranto on 01/01/2021.
 * */
class FavoriteViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getAllLocalData(): LiveData<PagedList<Articles>>?= appRepository.getAllLocalData()?.toLiveData(20)
    fun listIsEmpty(): Boolean = getAllLocalData()?.value?.isEmpty() ?: true

}