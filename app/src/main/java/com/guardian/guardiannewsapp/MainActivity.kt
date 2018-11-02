package com.guardian.guardiannewsapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.guardian.guardiannewsapp.ui.adapters.viewholders.ArticleViewHolder
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(),
        HeadlinesFragment.OnBackSelectedListener,
        ArticleViewHolder.OnOpenWebViewListener,
        ArticleViewHolder.OnClickSectionListener {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bDownloadNews.setOnClickListener {
            searchContent()
        }

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return

        navController = host.navController
    }

    fun searchContent() {
        if (navController.currentDestination?.id == R.id.headlinesFragment) {
            navController.navigate(R.id.searchAgain, Bundle().apply {
                putString("searchTerm", editText.text.toString())
            })
        } else {
            navController.navigate(R.id.search, Bundle().apply {
                putString("searchTerm", editText.text.toString())
            })
        }
        editText.text.clear()
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

    override fun onBackButtonPressed() {
        navController.navigateUp()
    }

    override fun openWebViewListener(url: String) {
        Log.d("mainactivity", url)
        navController.navigate(R.id.openUrl, Bundle().apply {
            putString("url", url)
        })
    }

    override fun startSectionSearch(section: String) {
        navController.navigate(R.id.searchTerm, Bundle().apply {
            putString("searchTerm", section)
        })
        editText.text.clear()
    }
}


