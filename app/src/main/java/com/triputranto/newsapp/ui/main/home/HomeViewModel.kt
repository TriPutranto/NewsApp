package com.triputranto.newsapp.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.data.repository.AppRepository
import com.triputranto.newsapp.utils.NetworkState

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class HomeViewModel(private val appRepository: AppRepository) : ViewModel() {

    val networkState: LiveData<NetworkState> by lazy {
        appRepository.getNetworkState()
    }

    val topHeadlinePagedList: LiveData<PagedList<Articles>> by lazy {
        appRepository.getTopHeadlines()
    }

    fun listIsEmpty(): Boolean = topHeadlinePagedList.value?.isEmpty() ?: true

}