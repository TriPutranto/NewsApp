package com.triputranto.newsapp.base

import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
abstract class ApiObserver<T> constructor(private val compositeDisposable: CompositeDisposable) :
    Observer<T> {

    override fun onComplete() {}

    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onNext(t: T) {
        onResponse(t)
    }

    override fun onError(e: Throwable) {
        onFailure(e)
    }

    abstract fun onResponse(data: T)

    abstract fun onFailure(error: Throwable)

}