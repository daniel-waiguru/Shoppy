package com.danielwaiguru.shoppy.data.sources.remote

import com.danielwaiguru.shoppy.data.models.responses.ProductResponse
import com.danielwaiguru.shoppy.data.models.responses.ProductsResponse
import com.danielwaiguru.shoppy.data.sources.remote.api.ShoppyApiService
import javax.inject.Inject

interface RemoteDataSource {
    suspend fun getProducts(): ProductsResponse

    suspend fun getProductById(productId: Int): ProductResponse
}

internal class RetrofitDataSource @Inject constructor(
    private val apiService: ShoppyApiService
) : RemoteDataSource {
    override suspend fun getProducts(): ProductsResponse =
        apiService.getProducts()

    override suspend fun getProductById(productId: Int): ProductResponse =
        apiService.getProductById(productId)
}