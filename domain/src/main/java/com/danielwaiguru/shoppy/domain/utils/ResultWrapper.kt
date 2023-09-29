package com.danielwaiguru.shoppy.domain.utils

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(
        val code: Int? = null,
        val errorMessage: String?
    ) : ResultWrapper<Nothing>()

    object Loading : ResultWrapper<Nothing>()
}