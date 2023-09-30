package com.danielwaiguru.shoppy.testing.test_data

import com.danielwaiguru.shoppy.data.models.dtos.ProductDto
import com.danielwaiguru.shoppy.domain.models.Product

fun testProduct(
    id: Int = 0,
    quantity: Int = 1,
    price: Int = 600
) = Product(
    currencyCode = "alia",
    description = "posidonium",
    id = id,
    imageLocation = "commune",
    name = "Robin Sykes",
    price = price,
    quantity = quantity

)

fun testProductDto(
    id: Int = 0,
    quantity: Int = 1,
    price: Int = 600
) = ProductDto(
    currencyCode = "alia",
    description = "posidonium",
    id = id,
    imageLocation = "commune",
    name = "Robin Sykes",
    price = price,
    quantity = quantity,
    currencySymbol = "$",
    status = "ACTIVE"
)

val notFoundError = "Resource not found"
