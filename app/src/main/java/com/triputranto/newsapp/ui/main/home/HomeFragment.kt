package com.triputranto.newsapp.ui.main.home

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triputranto.newsapp.R
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.ui.main.MainAdapter
import com.triputranto.newsapp.utils.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.status_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var mainAdapter: MainAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        if (verifyAvailableNetwork(context)) {
            setupData()
            observeViewModel()
        } else {
            setupNetworkState(resources.getString(R.string.offline_network), R.drawable.ic_baseline_network_check_24)
        }
    }

    private fun observeViewModel() {
        observe(viewModel.articles) {
            rv_home.show()
            if (it.size >= 20) {
                initListener(it)
                mainAdapter.setListItem(it.subList(0, 20))
            } else {
                mainAdapter.setListItem(it)
            }
        }
        observe(viewModel.networkState) {
            when (it) {
                NetworkState.LOADING -> {
                    rv_home.show()
                    rl_status.hide()
                }
                NetworkState.LOADED -> {
                    rl_status.hide()
                }
                NetworkState.EMPTY -> {
                    setupNetworkState(it.message, R.drawable.ic_baseline_youtube_searched_for_24)
                }
                NetworkState.ERROR -> {
                    setupNetworkState(it.message, R.drawable.ic_baseline_error_24)
                }
            }
        }
    }

    private fun initListener(list: List<Articles>) {
        rv_home.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val countItem = linearLayoutManager.itemCount
                val lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition()
                val isLastPosition = countItem.minus(1) == lastVisiblePosition
                if (isLastPosition && countItem < list.size) {
                    pb_home.show()
                    Handler().postDelayed({
                        mainAdapter.setListItem(list)
                        pb_home.hide()
                    }, 1000)
                }
            }
        })
    }

    private fun setupNetworkState(message: String, icon: Int) {
        rv_home.hide()
        rl_status.show()
        iv_status.load(icon)
        tv_status.text = message
        mb_status.show()
        mb_status.text = resources.getString(R.string.reload)
        mb_status.setOnClickListener {
            if (verifyAvailableNetwork(context)) {
                setupData()
                observeViewModel()
            }
        }
    }

    private fun setupData() {
        viewModel.getTopHeadlines("us")
    }

    private fun setupView() {
        mainAdapter = MainAdapter {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        rv_home.apply {
            this.adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    @Suppress("DEPRECATION")
    private fun verifyAvailableNetwork(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected && networkInfo.isAvailable
    }

}