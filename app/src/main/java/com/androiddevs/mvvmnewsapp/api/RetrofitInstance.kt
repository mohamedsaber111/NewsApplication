package com.androiddevs.mvvmnewsapp.api

import com.androiddevs.mvvmnewsapp.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        //lazy means we just initialize this here once
        private val retrofit by lazy {
            //we add dependencies for log responses of retrofit which very useful for debugging
            //LoggingInterceptor >> handle or modify response
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            //add network clint
            val clint =OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clint)
                .build()
        }

        //get Api instance from retrofit
        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }

    }
}