package com.danielwaiguru.shoppy.presentation.common

data class ShoppyUIState<T>(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val value: T
)
