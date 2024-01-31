package com.example.newsapp.models


data class NewsResponse(
    var articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)