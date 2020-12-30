package com.triputranto.newsapp.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.triputranto.newsapp.base.BaseViewModel
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.data.repository.AppRepository
import com.triputranto.newsapp.utils.NetworkState

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class HomeViewModel(private val appRepository: AppRepository) : BaseViewModel() {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _articles = MutableLiveData<List<Articles>>()
    val articles: LiveData<List<Articles>>
        get() = _articles

    fun getTopHeadlines(country: String) {
        _networkState.postValue(NetworkState.LOADING)
        appRepository.getTopHeadlines(country).subscribe({
            it.let {
                _networkState.postValue(NetworkState.LOADED)
                _articles.postValue(it.list)
            }
        }, {
            _networkState.postValue(NetworkState.ERROR)
        }).addTo(compositeDisposable)
    }
}