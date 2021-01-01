package com.triputranto.newsapp.data.source.remote

import androidx.lifecycle.MutableLiveData
import com.triputranto.newsapp.base.ApiObserver
import com.triputranto.newsapp.base.PagedKeyObserver
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.data.entity.BaseResponse
import com.triputranto.newsapp.utils.Const.FIRST_PAGE
import com.triputranto.newsapp.utils.Const.POST_PER_PAGE
import com.triputranto.newsapp.utils.Const.STATUS_ERROR
import com.triputranto.newsapp.utils.Const.STATUS_SUCCESS
import com.triputranto.newsapp.utils.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class RemoteDataSource constructor(
    private val apiService: ApiService,
    private val compositeDisposable: CompositeDisposable
) : PagedKeyObserver() {

    private var articles = Articles()

    fun setArticle(articles: Articles) {
        this.articles = articles
    }

    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    private var page = FIRST_PAGE

    override fun mLoadInitial(callback: LoadInitialCallback<Int, Articles>) {
        networkState.postValue(NetworkState.LOADING)
        apiService.getTopHeadlines(articles.country, page, POST_PER_PAGE, articles.query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<BaseResponse<Articles>>(compositeDisposable) {
                override fun onResponse(data: BaseResponse<Articles>) {
                    networkState.postValue(NetworkState.LOADED)
                    when (data.status) {
                        STATUS_SUCCESS -> {
                            if (!data.list.isNullOrEmpty()) {
                                callback.onResult(data.list, null, page + 1)
                            } else {
                                networkState.postValue(NetworkState.EMPTY)
                            }
                        }
                        STATUS_ERROR -> {
                            networkState.postValue(NetworkState.ERROR)
                        }
                    }

                }

                override fun onFailure(error: Throwable) {
                    networkState.postValue(NetworkState.ERROR)
                }

            })
    }

    override fun mLoadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {
        apiService.getTopHeadlines(articles.country, params.key, POST_PER_PAGE, articles.query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : ApiObserver<BaseResponse<Articles>>(compositeDisposable) {
                override fun onResponse(data: BaseResponse<Articles>) {
                    networkState.postValue(NetworkState.LOADED)
                    when (data.status) {
                        STATUS_SUCCESS -> {
                            if (!data.list.isNullOrEmpty()) {
                                if (params.key * 20 >= data.totalResults) {
                                    networkState.postValue(NetworkState.ENDOFLIST)
                                } else {
                                    callback.onResult(data.list, params.key + 1)
                                }
                                callback.onResult(data.list, params.key + 1)
                            } else {
                                networkState.postValue(NetworkState.EMPTY)
                            }
                        }
                        STATUS_ERROR -> {
                            networkState.postValue(NetworkState.ERROR)
                        }
                    }

                }

                override fun onFailure(error: Throwable) {
                    networkState.postValue(NetworkState.ERROR)
                }

            })
    }

    fun getSearchTopHeadlines(query: String) = apiService.getTopHeadlines(query = query)
}