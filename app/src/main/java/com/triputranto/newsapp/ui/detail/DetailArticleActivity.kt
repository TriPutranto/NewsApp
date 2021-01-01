package com.triputranto.newsapp.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.triputranto.newsapp.R
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.utils.load
import kotlinx.android.synthetic.main.activity_detail_article.*
import org.koin.android.ext.android.inject

/**
 * Created by Ahmad Tri Putranto on 01/01/2021.
 * */
class DetailArticleActivity : AppCompatActivity() {
    private var isFavorite: Boolean = false
    private val detailViewModel: DetailViewModel by inject()
    private var mArticles = Articles()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_article)
        setupView()
        checkFavorite()
    }

    private fun setupView() {
        val article = intent.getParcelableExtra<Articles>(KEY_ARTICLE)
        article?.let { mArticles = it }
        iv_articles.load(article?.urlToImage)
        tv_title.text = article?.title
        tv_author.text = article?.author
        tv_published.text = article?.getPublished(article.publishedAt.toString())
        tv_description.text = article?.description
        tv_content.text = article?.content
        tv_source.text = article?.source?.name
        iv_favorite.setOnClickListener {
            if (isFavorite) {
                Toast.makeText(this, "Remove", Toast.LENGTH_SHORT).show()
                detailViewModel.removeFavorite(mArticles.title.toString())
            } else {
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                detailViewModel.addToFavorite(mArticles)
            }
            checkFavorite()
        }
    }

    private fun checkFavorite() {
        detailViewModel.getLocalData(mArticles.title.toString()).observe(
            this,
            Observer {
                isFavorite = it != null
                setFavorite()
            })
    }

    private fun setFavorite() {
        if (isFavorite) {
            iv_favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            iv_favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    companion object {
        const val KEY_ARTICLE = "key_article"
    }

}