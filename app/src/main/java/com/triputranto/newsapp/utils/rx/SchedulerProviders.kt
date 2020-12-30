package com.triputranto.newsapp.utils.rx

import io.reactivex.Scheduler

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
interface SchedulerProviders {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun trampoline(): Scheduler
    fun newThread(): Scheduler
    fun io(): Scheduler
}