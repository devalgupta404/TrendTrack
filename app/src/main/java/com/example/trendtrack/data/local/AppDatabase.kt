package com.example.trendtrack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trendtrack.data.model.Article
import com.example.trendtrack.data.model.Source

@Database(
    entities = [Article::class, Source::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun articleDao(): ArticleDao
} 