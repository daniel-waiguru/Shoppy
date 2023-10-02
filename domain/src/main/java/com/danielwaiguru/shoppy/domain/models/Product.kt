package com.danielwaiguru.shoppy.domain.models

data class Product(
    val currencySymbol: String,
    val description: String,
    val id: Int,
    val imageLocation: String,
    val name: String,
    val price: Int,
    val quantity: Int
)
