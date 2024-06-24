package com.example.sampapp1.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampapp1.R
import com.example.sampapp1.data.model.NewsArticle

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var articles = listOf<NewsArticle>()

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.titleTextView.text = article.title
    }

    override fun getItemCount() = articles.size

    fun setArticles(articles: List<NewsArticle>) {
        this.articles = articles
        notifyDataSetChanged()
    }
}