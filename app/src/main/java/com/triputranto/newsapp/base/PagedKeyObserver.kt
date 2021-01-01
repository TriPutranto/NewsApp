package com.triputranto.newsapp.base

import androidx.paging.PageKeyedDataSource
import com.triputranto.newsapp.data.entity.Articles

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
abstract class PagedKeyObserver : PageKeyedDataSource<Int, Articles>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Articles>
    ) {
        mLoadInitial(callback)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {
        mLoadAfter(params, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {
    }

    abstract fun mLoadInitial(callback: LoadInitialCallback<Int, Articles>)

    abstract fun mLoadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>)

}