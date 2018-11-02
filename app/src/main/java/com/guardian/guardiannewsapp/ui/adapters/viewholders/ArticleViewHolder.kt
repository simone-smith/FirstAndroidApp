package com.guardian.guardiannewsapp.ui.adapters.viewholders

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.guardian.guardiannewsapp.R
import com.guardian.guardiannewsapp.models.ArticleItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleViewHolder(itemView: View, private val onOpenWebViewListener: OnOpenWebViewListener) : RecyclerView.ViewHolder(itemView) {

    interface OnOpenWebViewListener {
        fun openWebViewListener(url: String)
    }

    fun bind(articleItem: ArticleItem) {
        itemView.tvArticleTitle.text = articleItem.webTitle

        if (articleItem.fields.thumbnail == null) {
            itemView.ivArticleImage.setImageResource(R.drawable.guardian)
        } else {
            Picasso.get().load(articleItem.fields.thumbnail).into(itemView.ivArticleImage)
        }

        itemView.tvPillarName.text = articleItem.pillarName

        when (articleItem.pillarName) {
            "News" -> itemView.tvPillarName.setTextColor(Color.parseColor("#c70000"))
            "Opinion" -> itemView.tvPillarName.setTextColor(Color.parseColor("#e05e00"))
            "Sport" -> itemView.tvPillarName.setTextColor(Color.parseColor("#0084c6"))
            "Arts" -> itemView.tvPillarName.setTextColor(Color.parseColor("#a1845c"))
            "Lifestyle" -> itemView.tvPillarName.setTextColor(Color.parseColor("#bb3b80"))
            else -> itemView.tvPillarName.setTextColor(Color.BLACK)
        }

        itemView.tvTrailText.text = articleItem.fields.trailText

        itemView.tvArticleLink.setOnClickListener {
            Log.d("ArticleViewHolder", articleItem.webUrl)
            onOpenWebViewListener.openWebViewListener(articleItem.webUrl)
        }

        itemView.tvSectionName.text = articleItem.sectionName
    }
}
