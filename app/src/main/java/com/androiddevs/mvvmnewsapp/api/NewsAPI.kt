package com.androiddevs.mvvmnewsapp.api

import com.androiddevs.mvvmnewsapp.models.NewsResponse
import com.androiddevs.mvvmnewsapp.util.Constants.Companion.API_Key
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        //which country we want to get the breaking news(we find it in documentation of news Api)
        @Query("country")
        countryCode: String = "us",
        //we don't get breakingNews at once because that would just be too much data instead we get 20 articles
        //and if we want get next 20 articles we will request page2 and ...
        @Query("page")
        pageNumber: Int = 1,
        //we want to include Api key in request so that newsApi knows how makes that request
        @Query("apiKey")
        apiKey:String= API_Key
    ): Response<NewsResponse>

    //search for news

    @GET("v2/everything")
    suspend fun searchForNews(
        //which country we want to get the breaking news(we find it in documentation of news Api)
        @Query("q")
        searchQuery: String ,
        //we don't get breakingNews at once because that would just be too much data instead we get 20 articles
        //and if we want get next 20 articles we will request page2 and ...
        @Query("page")
        pageNumber: Int = 1,
        //we want to include Api key in request so that newsApi knows how makes that request
        @Query("apiKey")
        apiKey:String= API_Key
    ): Response<NewsResponse>


}