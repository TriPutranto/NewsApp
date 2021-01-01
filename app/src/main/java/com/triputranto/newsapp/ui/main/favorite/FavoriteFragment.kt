package com.triputranto.newsapp.ui.main.favorite

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.triputranto.newsapp.R
import com.triputranto.newsapp.base.BaseFragment
import com.triputranto.newsapp.ui.main.MainAdapter
import com.triputranto.newsapp.utils.hide
import com.triputranto.newsapp.utils.load
import com.triputranto.newsapp.utils.show
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.status_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var mainAdapter: MainAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar(toolbar)
        mainAdapter = MainAdapter()
        rv_favorite.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
        }
        favoriteViewModel.getAllLocalData()?.observe(viewLifecycleOwner, Observer {
            rl_status.hide()
            mainAdapter.submitList(it)
        })
        if (favoriteViewModel.listIsEmpty()) {
            rl_status.show()
            tv_status.text = resources.getString(R.string.empty)
            iv_status.load(R.drawable.ic_baseline_hourglass_empty_24)
        }
    }
}