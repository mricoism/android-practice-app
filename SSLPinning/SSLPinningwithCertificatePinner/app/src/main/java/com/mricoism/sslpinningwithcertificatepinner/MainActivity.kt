package com.mricoism.sslpinningwithcertificatepinner

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mricoism.sslpinningwithcertificatepinner.models.NewsResponse
import com.mricoism.sslpinningwithcertificatepinner.util.Constants
import kotlinx.coroutines.runBlocking
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val retrofit = createRetrofit()
        val apiService = retrofit.create(ApiService::class.java)

        runBlocking {
            try {
                val response = apiService.getHeadlines()
                if (response.isSuccessful) {
                    println(response.body())
                } else {
                    println("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

fun createOkHttpClient(): OkHttpClient {
    // Define the pinned certificate for your server
    val certificatePinner = CertificatePinner.Builder()
        .add("www.newsapi.org", "sha256/7a0e118f82c9efe2b51d9dc5f12932fde0bdabb064612c492f5ebf26d5e1732e") // Replace with your SHA-256 hash
        .build()

    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .certificatePinner(certificatePinner)
        .build()
}

fun createRetrofit(): Retrofit {
    val okHttpClient = createOkHttpClient()

    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL) // Replace with your base URL
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country")
        country: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY

    ): Response<NewsResponse>
}