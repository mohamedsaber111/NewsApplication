package com.example.newsapp.util

//class recommended by google used to wrap around our network responses between successful or error responses,
//and help us to handle loading state when we make response that we can show on progress bar
sealed class Resource<T>(
    //data is body of response
    val data: T? = null,

    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    //TODO if we put message only as parameter we get error because Resource() class has T not String
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    class Loading<T> : Resource<T>()
}

