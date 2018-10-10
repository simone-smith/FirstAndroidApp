package com.guardian.guardiannewsapp.models

data class ArticleItem(
        val id: String,
        val webTitle: String,
        val webUrl: String,
        val sectionName: String,
        val pillarName: String,
        val fields: Fields
)

data class Fields(val thumbnail: String?)
