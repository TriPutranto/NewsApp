package com.triputranto.newsapp.ui.main

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.triputranto.newsapp.R
import com.triputranto.newsapp.ui.main.favorite.FavoriteFragment
import com.triputranto.newsapp.ui.main.home.HomeFragment
import com.triputranto.newsapp.ui.main.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class MainActivity : AppCompatActivity() {
    private val home: HomeFragment by inject()
    private val search: SearchFragment by inject()
    private val favorite: FavoriteFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView(savedInstanceState)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val item1 = AHBottomNavigationItem(
            R.string.home,
            R.drawable.ic_baseline_home_24,
            R.color.white
        )
        val item2 = AHBottomNavigationItem(
            R.string.search,
            R.drawable.ic_baseline_search_24,
            R.color.white
        )

        val item3 = AHBottomNavigationItem(
            R.string.favorite,
            R.drawable.ic_baseline_favorite_24,
            R.color.white
        )
        bottom_navigation.addItem(item1)
        bottom_navigation.addItem(item2)
        bottom_navigation.addItem(item3)
        bottom_navigation.accentColor = ContextCompat.getColor(this, R.color.white)
        bottom_navigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
        bottom_navigation.currentItem = 0
        bottom_navigation.inactiveColor = Color.parseColor("#99FFFFFF")
        bottom_navigation.isTranslucentNavigationEnabled = true

        bottom_navigation.setOnTabSelectedListener { position, wasSelected ->
            when (position) {
                0 -> if (!home.isVisible) {
                    setupTransactionVisibility(home, search, favorite)
                } else if (wasSelected) {
                    rv_home.smoothScrollToPosition(0)
                }
                1 -> if (!search.isVisible) {
                    setupTransactionVisibility(search, home, favorite)
                }
                2 -> if (!favorite.isVisible) {
                    setupTransactionVisibility(favorite, search, home)
                }
            }
            true
        }
    }

    private fun setView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.main_container, home, "1")
                .add(R.id.main_container, search, "2").hide(search)
                .add(R.id.main_container, favorite, "3").hide(favorite)
            fragmentTransaction.commit()
        }
    }

    private fun setupTransactionVisibility(
        showItem: Fragment,
        hideFrag1: Fragment,
        hideFrag2: Fragment
    ) {
        supportFragmentManager.beginTransaction()
            .show(showItem)
            .hide(hideFrag1)
            .hide(hideFrag2)
            .commit()
    }
}