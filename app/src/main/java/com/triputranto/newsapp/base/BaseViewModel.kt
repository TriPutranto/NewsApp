package com.triputranto.newsapp.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    protected val compositeDisposable by lazy { CompositeDisposable() }
    override fun onCleared() {
        with(compositeDisposable) {
            clear()
            dispose()
        }
        super.onCleared()
    }

    fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(this)
    }
}