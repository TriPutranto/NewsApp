package com.triputranto.newsapp.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.triputranto.newsapp.R
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.ui.detail.DetailArticleActivity
import com.triputranto.newsapp.ui.detail.DetailArticleActivity.Companion.KEY_ARTICLE
import com.triputranto.newsapp.utils.NetworkState
import com.triputranto.newsapp.utils.hide
import com.triputranto.newsapp.utils.load
import com.triputranto.newsapp.utils.show
import kotlinx.android.synthetic.main.layout_item_main.view.*
import kotlinx.android.synthetic.main.network_state_item.view.*

/**
 * Created by Ahmad Tri Putranto on 29/12/2020.
 * */
class MainAdapter : PagedListAdapter<Articles, RecyclerView.ViewHolder>(ArticlesDiffCallback()) {

    companion object {
        const val ARTICLES_VIEW_TYPE = 1
        const val NETWORK_VIEW_TYPE = 2
    }

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View
        return if (viewType == ARTICLES_VIEW_TYPE) {
            view = layoutInflater.inflate(R.layout.layout_item_main, parent, false)
            ArticlesItemViewHolder(view)
        } else {
            view = layoutInflater.inflate(R.layout.network_state_item, parent, false)
            NetworkStateItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ARTICLES_VIEW_TYPE) {
            (holder as ArticlesItemViewHolder).bind(getItem(position))
        } else {
            (holder as NetworkStateItemViewHolder).bind(networkState)
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            NETWORK_VIEW_TYPE
        } else {
            ARTICLES_VIEW_TYPE
        }
    }

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()

        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    class NetworkStateItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(networkState: NetworkState?) {
            with(itemView) {
                if (networkState != null && networkState == NetworkState.LOADING) {
                    progress_bar_item.show()
                } else {
                    progress_bar_item.hide()
                }
                if (networkState != null && networkState == NetworkState.ERROR) {
                    error_msg_item.show()
                    error_msg_item.text = networkState.message
                } else if (networkState != null && networkState == NetworkState.ENDOFLIST) {
                    error_msg_item.show()
                    error_msg_item.text = networkState.message
                } else if (networkState != null && networkState == NetworkState.EMPTY) {
                    error_msg_item.hide()
                    error_msg_item.text = networkState.message
                } else {
                    error_msg_item.hide()
                }
            }
        }
    }

    class ArticlesItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(articles: Articles?) {
            with(itemView) {
                iv_articles.load(articles?.urlToImage)
                tv_title.text = articles?.title
                tv_author.text = articles?.author
                tv_published.text = articles?.getPublished(articles.publishedAt.toString())
                tv_source.text = articles?.source?.name
                setOnClickListener {
                    val intent = Intent(context, DetailArticleActivity::class.java)
                    intent.putExtra(KEY_ARTICLE, articles)
                    context.startActivity(intent)
                }
            }
        }
    }

    class ArticlesDiffCallback : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean =
            oldItem == newItem
    }
}

