package com.androiddevs.mvvmnewsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.models.NewsResponse
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(val newsRepository : NewsRepository) : ViewModel() {

    val breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val breakingNewsPage =1

    val searchNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNewsPage =1

    init {

        getBreakingNews("us")
    }
    fun getBreakingNews(countryCode :String)= viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())

        val response =newsRepository.getNewsFromApi(countryCode,breakingNewsPage)

        breakingNews.postValue(handlingBreakingNewsResponse(response))

    }

    fun searchNews(searchQuery : String)= viewModelScope.launch {
        searchNews.postValue(Resource.Loading())

        val response = newsRepository.searchNews(searchQuery,searchNewsPage)
        searchNews.postValue(handlingSearchNewsResponse(response))
    }

    private fun handlingBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let {resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message(),response.body())
    }

    private fun handlingSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let {resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message(),response.body())
    }



}