package com.androiddevs.mvvmnewsapp.repository

import com.androiddevs.mvvmnewsapp.api.RetrofitInstance
import com.androiddevs.mvvmnewsapp.dp.ArticleDatabase

//get data from database and remote data source from api
class NewsRepository(val db: ArticleDatabase)  {


    suspend fun getNewsFromApi(countryCode : String, pageNumber : Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery :String,pageNumber :Int)=
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)
}