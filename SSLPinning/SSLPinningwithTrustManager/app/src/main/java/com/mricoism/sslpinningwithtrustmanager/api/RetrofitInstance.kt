package com.mricoism.sslpinning.api

import android.util.Log
import com.mricoism.sslpinning.util.Constants.Companion.BASE_URL
import com.mricoism.sslpinningwithtrustmanager.MainActivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.security.KeyStore
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            // Load the certificate from raw resources
//            val certificateInputStream: InputStream = context.resources.openRawResource(R.raw.your_certificate)
            val certificateInputStream: InputStream? = MainActivity::class.java.getResourceAsStream("/res/raw/newsapi.pem")

            // Create a KeyStore and load the certificate
            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            keyStore.load(null) // Initialize an empty KeyStore
            val certificateFactory = java.security.cert.CertificateFactory.getInstance("X.509")
            val certificate = certificateFactory.generateCertificate(certificateInputStream)
            keyStore.setCertificateEntry("server", certificate)

            // Initialize TrustManagerFactory with the KeyStore
            val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(keyStore)

            // Get the TrustManager and set up SSLContext
            val trustManagers = trustManagerFactory.trustManagers
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustManagers, null)

            val client = OkHttpClient.Builder()
                .sslSocketFactory(sslContext.socketFactory, trustManagers[0] as X509TrustManager)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }) // Optional: Add logging
                .build()

            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build()
        }

        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}