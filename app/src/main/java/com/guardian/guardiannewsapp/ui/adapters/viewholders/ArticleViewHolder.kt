package com.guardian.guardiannewsapp.ui.adapters.viewholders

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.guardian.guardiannewsapp.R
import com.guardian.guardiannewsapp.models.ArticleItem
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(articleItem: ArticleItem) {
        itemView.tvArticleTitle.text = articleItem.webTitle

        itemView.ivArticleImage.setImageResource(R.drawable.guardian)

        itemView.tvPillarName.text = articleItem.pillarName

        itemView.tvSectionName.text = articleItem.sectionName

        itemView.tvArticleLink.setOnClickListener {
            Log.d("ArticleViewHolder", articleItem.webUrl)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(articleItem.webUrl))
            itemView.context.startActivity(intent)
        }
    }
}
