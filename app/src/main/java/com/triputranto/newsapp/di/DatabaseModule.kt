package com.triputranto.newsapp.di

import com.triputranto.newsapp.data.source.local.AppDatabase
import com.triputranto.newsapp.data.source.local.LocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Ahmad Tri Putranto on 01/01/2021.
 * */
val databaseModule = module {
    single {
        AppDatabase.getInstance(androidApplication())
    }

    single {
        get<AppDatabase>().appDao()
    }

    single {
        LocalDataSource(get())
    }
}