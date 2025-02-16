package com.techies.threatdetectiondemo.domain.model

sealed class Result<out T> {
    data class Success<T>(val data: T, val code: Int? = 200) : Result<T>()
    data class Error(val code: Int, val message: String?) : Result<Nothing>()
    data class Loading(val message: String?) : Result<Nothing>()
}