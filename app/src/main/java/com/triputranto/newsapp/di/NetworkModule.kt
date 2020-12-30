package com.triputranto.newsapp.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.triputranto.newsapp.BuildConfig.API_KEY
import com.triputranto.newsapp.BuildConfig.BASE_URL
import com.triputranto.newsapp.data.source.remote.ApiService
import com.triputranto.newsapp.data.source.remote.RemoteDataSource
import com.triputranto.newsapp.utils.Const.NETWORK_TIMEOUT
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
val networkModule = module {

    single {
        OkHttpClient().newBuilder()
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .retryOnConnectionFailure(true)
            .addInterceptor { chain ->
                val url =
                    chain.request().url.newBuilder().addQueryParameter("apiKey", API_KEY).build()
                val request = chain.request().newBuilder().url(url).build()
                chain.proceed(request)
            }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ApiService::class.java)
    }

    single {
        RemoteDataSource(get())
    }

}
