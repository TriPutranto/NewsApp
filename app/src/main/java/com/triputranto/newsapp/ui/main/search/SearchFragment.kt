package com.triputranto.newsapp.ui.main.search

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.triputranto.newsapp.R
import com.triputranto.newsapp.utils.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.status_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class SearchFragment : Fragment(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var searchAdapter: SearchAdapter
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        observeChange()
    }

    private fun setupView() {
        searchAdapter = SearchAdapter()
        rv_search.apply {
            this.adapter = searchAdapter
            layoutManager = LinearLayoutManager(context)
        }
        sv_article.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                if (s != null) {
                    if (s != "") {
                        rv_search.show()
                        viewModel.getSearch(s)
                        sv_article.clearFocus()
                    }

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == "") {
                    searchAdapter.clearResult()
                    rv_search.hide()
                }
                return false
            }

        })
    }

    private fun observeChange() {
        observe(viewModel.search) {
            rv_search.show()
            searchAdapter.setResult(it)
        }
        observe(viewModel.networkState) {
            when (it) {
                NetworkState.LOADING -> {
                    rv_search.hide()
                    rl_status.hide()
                    pb_search.show()

                }
                NetworkState.LOADED -> {
                    rv_search.show()
                    pb_search.hide()
                }
                NetworkState.ERROR -> {
                    pb_search.hide()
                    mb_status.show()
                    setupNetworkState(it.message, R.drawable.ic_baseline_error_24)
                }
                NetworkState.EMPTY -> {
                    pb_search.hide()
                    setupNetworkState(it.message, R.drawable.ic_baseline_hourglass_empty_24)
                }
            }
        }
    }

    private fun setupNetworkState(message: String, icon: Int) {
        rv_search.hide()
        rl_status.show()
        iv_status.load(icon)
        tv_status.text = message
        mb_status.text = resources.getString(R.string.reload)
        mb_status.setOnClickListener {
            if (verifyAvailableNetwork(context)) {
                observeChange()
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun verifyAvailableNetwork(context: Context?): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected && networkInfo.isAvailable
    }

}