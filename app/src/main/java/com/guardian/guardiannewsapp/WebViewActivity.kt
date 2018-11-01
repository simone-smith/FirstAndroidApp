package com.guardian.guardiannewsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.guardian.guardiannewsapp.ui.adapters.viewholders.ArticleViewHolder

class WebViewActivity: AppCompatActivity() {

    lateinit var url: Uri

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_webview)

        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.loadUrl(url.toString())
    }


}