package com.guardian.guardiannewsapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.guardian.guardiannewsapp.R
import com.guardian.guardiannewsapp.models.ArticleItem
import com.guardian.guardiannewsapp.ui.adapters.viewholders.ArticleViewHolder

class ArticleAdapter(private val items: MutableList<ArticleItem>) : RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(viewHolder: ArticleViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    fun addAll(list: List<ArticleItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}
