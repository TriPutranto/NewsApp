package com.triputranto.newsapp

import android.app.Application
import com.triputranto.newsapp.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class AppController : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppController)
            modules(appComponent)
        }
    }
}