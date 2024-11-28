package com.mricoism.newsapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mricoism.newsapp.R
import com.mricoism.newsapp.databinding.ActivityNewsBinding
import com.mricoism.newsapp.db.ArticleDatabase
import com.mricoism.newsapp.repository.NewsRepository

class NewsActivity : AppCompatActivity() {

    lateinit var newsViewModel: NewsViewModel
    lateinit var binding: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        newsViewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navHostController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navHostController)










//        setContentView(R.layout.activity_news)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
        }
    }
}