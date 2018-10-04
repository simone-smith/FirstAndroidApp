package com.guardian.guardiannewsapp.ui.adapters.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.guardian.guardiannewsapp.models.ArticleItem
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(articleItem: ArticleItem) {
        itemView.tvArticleTitle.text = articleItem.webTitle
    }
}
