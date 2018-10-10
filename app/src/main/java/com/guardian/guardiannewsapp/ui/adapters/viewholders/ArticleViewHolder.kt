package com.guardian.guardiannewsapp.ui.adapters.viewholders

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.guardian.guardiannewsapp.R
import com.guardian.guardiannewsapp.models.ArticleItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(articleItem: ArticleItem) {
        itemView.tvArticleTitle.text = articleItem.webTitle

        if (articleItem.fields.thumbnail == null) {
            itemView.ivArticleImage.setImageResource(R.drawable.guardian)
        } else {
            Picasso.get().load(articleItem.fields.thumbnail).into(itemView.ivArticleImage)
        }

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
