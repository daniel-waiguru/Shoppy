package com.danielwaiguru.shoppy.presentation.features.products.models

import com.danielwaiguru.shoppy.domain.models.Product

data class ProductCartUIState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val product: Product? = null,
    val cartQuantity: Int = 1
)