package com.mricoism.sslpinningwithcertificatepinner.models

import java.io.Serializable

//@Entity(
//    "articles"
//)
data class Article(
//    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
): Serializable