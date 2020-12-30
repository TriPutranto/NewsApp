package com.triputranto.newsapp.utils.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class AppSchedulerProviders :
    SchedulerProviders {
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun computation() = Schedulers.computation()

    override fun trampoline() = Schedulers.trampoline()

    override fun newThread() = Schedulers.newThread()

    override fun io() = Schedulers.io()
}