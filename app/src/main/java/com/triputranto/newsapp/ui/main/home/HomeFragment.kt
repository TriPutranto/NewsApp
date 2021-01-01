package com.triputranto.newsapp.ui.main.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.triputranto.newsapp.R
import com.triputranto.newsapp.base.BaseFragment
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.data.source.remote.RemoteDataSource
import com.triputranto.newsapp.ui.main.MainAdapter
import com.triputranto.newsapp.utils.NetworkState
import com.triputranto.newsapp.utils.hide
import com.triputranto.newsapp.utils.load
import com.triputranto.newsapp.utils.show
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.status_layout.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var mainAdapter: MainAdapter
    private val remoteDataSource: RemoteDataSource by inject()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        if (verifyAvailableNetwork(context)) {
            observeViewModel()
        } else {
            setupNetworkState(
                resources.getString(R.string.offline_network),
                R.drawable.ic_baseline_network_check_24
            )
        }
    }

    private fun observeViewModel() {
        val articles = Articles(country = "us")
        remoteDataSource.setArticle(articles)
        viewModel.topHeadlinePagedList.observe(viewLifecycleOwner, Observer {
            rv_home.show()
            rl_status.hide()
            mainAdapter.submitList(it)
        })
        viewModel.networkState.observe(viewLifecycleOwner, Observer{
            when (it) {
                NetworkState.LOADING -> {
                    rv_home.hide()
                    pb_home.show()
                }
                NetworkState.LOADED -> {
                    rv_home.show()
                    pb_home.hide()
                }
                NetworkState.ERROR -> {
                    pb_home.hide()
                    setupNetworkState(it.message, R.drawable.ic_baseline_error_24)
                }
            }
            if (!viewModel.listIsEmpty()) mainAdapter.setNetworkState(it)
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
                observeViewModel()
            }
        }
    }

    private fun setupView() {
        setupToolbar(toolbar)
        mainAdapter = MainAdapter()
        rv_home.apply {
            this.adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}