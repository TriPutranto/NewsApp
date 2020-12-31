package com.triputranto.newsapp.utils

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class NetworkState(val message: String) {
    companion object {
        val LOADED: NetworkState = NetworkState("Success")
        val EMPTY: NetworkState = NetworkState("Data is empty")
        val LOADING: NetworkState = NetworkState("Running")
        val ERROR: NetworkState = NetworkState("Something went wrong")
    }
}