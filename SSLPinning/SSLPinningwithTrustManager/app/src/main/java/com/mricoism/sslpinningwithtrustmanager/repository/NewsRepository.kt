package com.mricoism.sslpinning.repository

import com.mricoism.sslpinning.api.RetrofitInstance

class NewsRepository {

    suspend fun getHeadlines(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getHeadlines(countryCode, pageNumber)
}