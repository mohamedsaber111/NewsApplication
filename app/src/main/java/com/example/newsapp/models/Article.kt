package com.example.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "articles"
)
//because article class not primitive then we mark it as Serializable which tell kotlin we want to be able to
//pass this class between several fragment with navigation component
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    val author: String?,//null
    val content: String?,//not null
    val description: String?,//null
    val publishedAt: String?,//not null
    val source: Source?,
    val title: String?,//not null
    val url: String,
    val urlToImage: String?//null
) : Serializable