package com.guardian.guardiannewsapp.network

import com.guardian.guardiannewsapp.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("search")
    fun getNews(@Query("section") content: String) : Call<NewsResponse>
}
