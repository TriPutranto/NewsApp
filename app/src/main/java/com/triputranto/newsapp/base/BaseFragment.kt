package com.triputranto.newsapp.base

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.triputranto.newsapp.R

abstract class BaseFragment(layout: Int): Fragment(layout) {

    fun setupToolbar(toolbar: Toolbar?) {
        activity?.setActionBar(toolbar)
        if (activity?.actionBar != null) {
            toolbar?.setTitleTextColor(Color.parseColor("#EAEAEA"))
            activity?.actionBar?.title = activity?.resources?.getString(R.string.app_name)
        }
    }

    @Suppress("DEPRECATION")
    fun verifyAvailableNetwork(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected && networkInfo.isAvailable
    }
}