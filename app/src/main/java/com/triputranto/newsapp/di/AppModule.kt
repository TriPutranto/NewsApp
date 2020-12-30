package com.triputranto.newsapp.di

import com.triputranto.newsapp.utils.rx.AppSchedulerProviders
import com.triputranto.newsapp.utils.rx.SchedulerProviders
import org.koin.dsl.module

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
val appModule = module {
    factory<SchedulerProviders> {
        AppSchedulerProviders()
    }
}