package com.guardian.guardiannewsapp.models

data class ArticleItem(
        val id: String,
        val webTitle: String,
        val webUrl: String,
        val pillarName: String,
        val fields: Fields,
        val sectionName: String
)

data class Fields(val thumbnail: String?, val trailText: String?)
