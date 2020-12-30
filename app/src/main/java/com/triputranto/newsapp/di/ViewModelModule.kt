package com.triputranto.newsapp.di

import com.triputranto.newsapp.ui.main.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
val viewModelModule = module {
    viewModel{HomeViewModel(get())}
}