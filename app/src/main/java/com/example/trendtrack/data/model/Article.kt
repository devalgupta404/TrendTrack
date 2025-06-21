package com.example.trendtrack.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "articles",
    foreignKeys = [
        ForeignKey(
            entity = Source::class,
            parentColumns = ["id"],
            childColumns = ["sourceId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title : String,
    val description: String?,
    val content: String?,
    @SerializedName("url")
    val url : String,
    @SerializedName("urlToImage")
    val urlToImage: String?,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: Source? = null,
    val sourceId : String = source?.id ?: "unknown",
    val sourceName: String = source?.name ?: "Unknown Source",
    var isBookmarked : Boolean = false
)
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
) 