package com.mricoism.sslpinningwithtrustmanager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mricoism.sslpinning.repository.NewsRepository
import kotlinx.coroutines.runBlocking
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val newsRepository = NewsRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        runBlocking {
            headlinesInternet("us")
        }
    }
    private suspend fun headlinesInternet(countryCode: String) {
        try {
            val response = newsRepository.getHeadlines(countryCode, 1)
        } catch (t: Throwable) {
            when(t) {
                is IOException -> println("IOException")
                else -> println("Others")
            }
        }
    }
}