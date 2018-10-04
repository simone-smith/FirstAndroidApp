package com.guardian.guardiannewsapp.models

data class Response(val results: List<ArticleItem>)

data class NewsResponse(val response: Response)
