package com.example.trendtrack.data.local

import androidx.room.*
import com.example.trendtrack.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao{
    @Query("SELECT * FROM articles WHERE isBookmarked = 1")
    fun getBookmarkedArticles(): Flow<List<Article>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)
    @Delete
    suspend fun deleteArticle(article: Article)
    @Query("UPDATE articles SET isBookmarked = :isBookmarked WHERE url = :url")
    suspend fun updateBookmarkStatus(url: String, isBookmarked: Boolean)
    @Query("SELECT * FROM articles WHERE sourceId = :sourceId")
    suspend fun getArticlesBySource(sourceId: String): List<Article>
} 