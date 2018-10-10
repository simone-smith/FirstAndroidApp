package com.guardian.guardiannewsapp.models


data class Response(val results: List<ArticleItem>, val total: Int)

data class NewsResponse(val response: Response)
