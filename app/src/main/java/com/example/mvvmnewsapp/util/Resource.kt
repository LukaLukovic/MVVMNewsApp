package com.example.mvvmnewsapp.util


//sealed klase su kao abstratkne samo sto mozemo da kazemo ko moze da inherituje od njih
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String,data: T? = null): Resource<T>(data, message)
    class Loading<T> :Resource<T>()

}