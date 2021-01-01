package com.triputranto.newsapp.ui.main.search

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.triputranto.newsapp.R
import com.triputranto.newsapp.data.entity.Articles
import com.triputranto.newsapp.ui.detail.DetailArticleActivity
import com.triputranto.newsapp.utils.*
import kotlinx.android.synthetic.main.layout_item_main.view.*

class SearchAdapter :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var result = ArrayList<Articles>()

    fun setResult(list: List<Articles>) {
        result.clear()
        result.addAll(list)
        notifyDataSetChanged()
    }

    fun clearResult() {
        result.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            parent.inflate(
                R.layout.layout_item_main
            )
        )

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(result[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(articles: Articles?) {
            with(itemView) {
                iv_articles.load(articles?.urlToImage)
                tv_title.text = articles?.title
                tv_author.text = articles?.author
                tv_source.text = articles?.source?.name
                setOnClickListener {
                    val intent = Intent(context, DetailArticleActivity::class.java)
                    intent.putExtra(DetailArticleActivity.KEY_ARTICLE, articles)
                    context.startActivity(intent)
                }
            }
        }
    }
}