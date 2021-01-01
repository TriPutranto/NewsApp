package com.triputranto.newsapp.di

import com.triputranto.newsapp.data.repository.AppRepository
import com.triputranto.newsapp.data.repository.AppRepositoryImpl
import org.koin.dsl.module

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
val repositoryModule = module {
    factory<AppRepository> {
        AppRepositoryImpl(get(), get(), get())
    }
}