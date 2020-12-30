package com.triputranto.newsapp.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.triputranto.newsapp.R
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.utils.inflate
import com.triputranto.newsapp.utils.load
import kotlinx.android.synthetic.main.layout_item_main.view.*

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class MainAdapter(private val listener: (String) -> Unit) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val articles = ArrayList<Articles>()

    companion object {
        const val ITEM_LOADING = 1
        const val ITEM_LOADED = 2
    }

    fun setListItem(list: List<Articles>) {
        articles.clear()
        articles.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (articles.isNullOrEmpty()) {
            ITEM_LOADING
        } else {
            ITEM_LOADED
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ITEM_LOADING -> {
                ViewHolder(parent.inflate(R.layout.layout_item_main_loading))
            }
            else -> ViewHolder(parent.inflate(R.layout.layout_item_main))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_LOADED -> {
                holder.bind(articles[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return if (!articles.isNullOrEmpty()) {
            articles.size
        } else {
            6
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(articles: Articles) {
            with(itemView) {
                iv_articles.load(articles.urlToImage)
                tv_title.text = articles.title
                tv_author.text = articles.author
                tv_published.text = articles.getPublished(articles.publishedAt.toString())
                tv_source.text = articles.source?.name
            }
        }
    }
}