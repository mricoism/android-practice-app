package com.mricoism.sslpinning.ui

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.mricoism.sslpinning.databinding.ActivityMainBinding
import com.mricoism.sslpinning.repository.NewsRepository
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val newsRepository = NewsRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            headlinesInternet("us")
        }

        val items = arrayOf("Apple", "Banana", "Cherry", "Date", "Grapes", "Mango")
        //create adapters
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            items,
        )
        binding.listView.adapter = adapter
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