package com.danielwaiguru.shoppy.data.test_data

import com.danielwaiguru.shoppy.data.models.responses.ProductResponse
import com.danielwaiguru.shoppy.data.models.responses.ProductsResponse
import com.danielwaiguru.shoppy.testing.test_data.testProductDto

val fakeProductsResponse: ProductsResponse = listOf(
    testProductDto(),
    testProductDto(id = 1, quantity = 89),
    testProductDto(id = 1, quantity = 90, price = 1000)
)
val fakeProductResponse: ProductResponse = testProductDto()
