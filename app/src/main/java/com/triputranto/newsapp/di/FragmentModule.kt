package com.triputranto.newsapp.di

import com.triputranto.newsapp.ui.main.favorite.FavoriteFragment
import com.triputranto.newsapp.ui.main.home.HomeFragment
import com.triputranto.newsapp.ui.main.search.SearchFragment
import org.koin.dsl.module

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
val fragmentModule = module {
    single {
        HomeFragment()
    }
    single {
        SearchFragment()
    }
    single {
        FavoriteFragment()
    }
}