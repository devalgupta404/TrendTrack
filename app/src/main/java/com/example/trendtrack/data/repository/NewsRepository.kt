package com.example.trendtrack.data.repository

import com.example.trendtrack.data.model.Article
import com.example.trendtrack.data.remote.NewsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val api: NewsApi
){
    suspend fun getNews(): List<Article> {
        return api.getNews().articles.map { articleDto ->
            Article(
                title = articleDto.title,
                description = articleDto.description,
                content = articleDto.content,
                url = articleDto.url,
                urlToImage = articleDto.urlToImage,
                publishedAt = articleDto.publishedAt,
                source = articleDto.source
            )
        }
    }
    
    suspend fun searchNews(query: String): List<Article>{
        return api.searchNews(query).articles.map { articleDto ->
            Article(
                title = articleDto.title,
                description = articleDto.description,
                content = articleDto.content,
                url = articleDto.url,
                urlToImage = articleDto.urlToImage,
                publishedAt = articleDto.publishedAt,
                source = articleDto.source
            )
        }
    }
} 