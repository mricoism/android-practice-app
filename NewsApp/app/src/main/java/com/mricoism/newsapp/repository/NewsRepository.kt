package com.mricoism.newsapp.repository

import com.mricoism.newsapp.api.RetrofitInstance
import com.mricoism.newsapp.db.ArticleDatabase
import com.mricoism.newsapp.models.Article

/*
what's the general meaning of repository ?
it means a place or a container where something is deposited or stored
 */
class NewsRepository(val db: ArticleDatabase) {

    suspend fun getHeadlines(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getHeadlines(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getFavouriteNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}