package com.triputranto.newsapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.data.entity.BaseResponse
import com.triputranto.newsapp.data.source.local.LocalDataSource
import com.triputranto.newsapp.data.source.remote.RemoteDataSource
import com.triputranto.newsapp.data.source.remote.RemoteDataSourceFactory
import com.triputranto.newsapp.utils.Const.POST_PER_PAGE
import com.triputranto.newsapp.utils.NetworkState
import io.reactivex.Observable

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class AppRepositoryImpl constructor(
    private val remoteDataSourceFactory: RemoteDataSourceFactory,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AppRepository {

    override fun getTopHeadlines(): LiveData<PagedList<Articles>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .setInitialLoadSizeHint(POST_PER_PAGE * 2)
            .build()
        return LivePagedListBuilder(remoteDataSourceFactory, config).build()
    }

    override fun getSearchTopHeadlines(query: String): Observable<BaseResponse<Articles>> {
        val remoteSource = remoteDataSource.getSearchTopHeadlines(query)
            .flatMap { Observable.just(it.asResult().data) }
            .onErrorResumeNext { t: Throwable -> return@onErrorResumeNext Observable.error(t) }
        return Observable.concatArrayEager(remoteSource)
    }

    override fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap(
        remoteDataSourceFactory.remoteLiveDataSource, RemoteDataSource::networkState
    )

    override fun insertToDatabase(articles: Articles) {
        localDataSource.insertToDatabase(articles)
    }

    override fun getAllLocalData(): DataSource.Factory<Int, Articles> =
        localDataSource.getAllLocalData()

    override fun getLocalDataByTitle(title: String): LiveData<Articles> =
        localDataSource.getLocalDataByTitle(title)

    override fun deleteLocalDataByTitle(title: String) {
        localDataSource.deleteLocalDataByTitle(title)
    }

    data class Result<out T>(
        val data: T? = null,
        val error: Throwable? = null
    )

    private fun <T> T.asResult(): Result<T> = Result(data = this, error = null)
}