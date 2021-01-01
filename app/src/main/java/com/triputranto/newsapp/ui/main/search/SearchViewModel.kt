package com.triputranto.newsapp.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.triputranto.newsapp.base.BaseViewModel
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.data.repository.AppRepository
import com.triputranto.newsapp.utils.Const.STATUS_SUCCESS
import com.triputranto.newsapp.utils.NetworkState

/**
 * Created by Ahmad Tri Putranto on 01/01/2021.
 * */
class SearchViewModel(private val appRepository: AppRepository) : BaseViewModel() {

    private val _search = MutableLiveData<List<Articles>>()
    val search: LiveData<List<Articles>>
        get() = _search

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    fun getSearch(query: String) {
        _networkState.postValue(NetworkState.LOADING)
        appRepository.getSearchTopHeadlines(query).subscribe({
            when (it.status) {
                STATUS_SUCCESS -> {
                    if (!it.list.isNullOrEmpty()) {
                        _search.postValue(it.list)
                        _networkState.postValue(NetworkState.LOADED)
                    } else {
                        _networkState.postValue(NetworkState.EMPTY)
                    }
                }
                else -> _networkState.postValue(NetworkState.ERROR)
            }
        }, {
            _networkState.postValue(NetworkState.ERROR)
        }).addTo(compositeDisposable)
    }

}