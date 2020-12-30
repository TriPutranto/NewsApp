package com.triputranto.newsapp.ui.main.home

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.triputranto.newsapp.R
import com.triputranto.newsapp.ui.main.MainAdapter
import com.triputranto.newsapp.utils.observe
import kotlinx.android.synthetic.main.fragment_home.*
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
        setupData()
        observeViewModel()
    }

    private fun observeViewModel() {
        observe(viewModel.articles) {
            mainAdapter.setListItem(it)
        }
        observe(viewModel.networkState) {

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

}