package com.example.trendtrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sources")
data class Source(
    @PrimaryKey
    val id: String = "",
    val name: String
) 