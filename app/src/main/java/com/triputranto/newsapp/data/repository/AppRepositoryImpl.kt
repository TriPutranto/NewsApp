package com.triputranto.newsapp.data.repository

import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.data.entity.BaseResponse
import com.triputranto.newsapp.data.source.remote.RemoteDataSource
import io.reactivex.Observable

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class AppRepositoryImpl constructor(private val remoteDataSource: RemoteDataSource) :
    AppRepository {

    override fun getTopHeadlines(country: String): Observable<BaseResponse<Articles>> {
        val remoteSource = remoteDataSource.getTopHeadlines(country)
            .flatMap { Observable.just(it.asResult().data) }
            .onErrorResumeNext { t: Throwable -> return@onErrorResumeNext Observable.error(t) }
        return Observable.concatArrayEager(remoteSource)
    }

    data class Result<out T>(
        val data: T? = null,
        val error: Throwable? = null
    )

    private fun <T> T.asResult(): Result<T> = Result(data = this, error = null)
}