package com.androiddevs.mvvmnewsapp.models

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
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    //room only handle primitive datatype(String) not another class
    //so we create type converter to convert Source class to ex "String" and string to Source so that room can treat with it
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
) : Serializable