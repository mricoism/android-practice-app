package com.mricoism.sslpinningwithcertificatepinner.models


data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)