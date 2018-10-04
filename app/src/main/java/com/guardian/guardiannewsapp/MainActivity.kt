package com.guardian.guardiannewsapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.gson.Gson
import com.guardian.guardiannewsapp.models.NewsResponse
import com.guardian.guardiannewsapp.network.NewsService
import com.guardian.guardiannewsapp.ui.adapters.ArticleAdapter
import kotlinx.android.synthetic.main.content_main.*
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val articleAdapter = ArticleAdapter(arrayListOf())

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request: Request = chain.request()
                    val url: HttpUrl = request.url().newBuilder().addQueryParameter("api-key", "5b40c5f3-c266-41c1-b80b-a60c25817e93").build()
                    chain.proceed(request.newBuilder().url(url).build())
                }
                .build()

    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .baseUrl("https://content.guardianapis.com")
                .build()
    }

    private val newsService: NewsService by lazy {
        retrofit.create(NewsService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(rvNewsItems) {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        button.setOnClickListener {
            startBusinessArticleDownload()
        }
        button2.setOnClickListener {
            startNewsArticleDownload()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun startBusinessArticleDownload() {
        button.visibility = View.GONE
        button2.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        tvErrorMessage.visibility = View.GONE
        val newsCall = newsService.getNews("business")
        newsCall.enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                displayError()
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.code() >= 400) {
                    displayError()
                } else {
                    val newsResponse = response.body()
                    progressBar.visibility = View.GONE
                    rvNewsItems.visibility = View.VISIBLE
                    newsResponse?.response?.results?.let { articleItems ->
                        articleAdapter.addAll(articleItems)
                    }
                }
            }

        })
    }

    fun startNewsArticleDownload() {
        button.visibility = View.GONE
        button2.visibility = View.GONE
        progressBar2.visibility = View.VISIBLE
        tvErrorMessage.visibility = View.GONE
        val newsCall = newsService.getNews("sport")
        newsCall.enqueue(object : Callback<NewsResponse> {
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                displayError()
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.code() >= 400) {
                    displayError()
                } else {
                    val newsResponse = response.body()
                    progressBar2.visibility = View.GONE
                    rvNewsItems.visibility = View.VISIBLE
                    newsResponse?.response?.results?.let { articleItems ->
                        articleAdapter.addAll(articleItems)
                    }
                }
            }

        })
    }

    fun displayError() {
        button.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        rvNewsItems.visibility = View.GONE
        tvErrorMessage.visibility = View.VISIBLE
    }
}


