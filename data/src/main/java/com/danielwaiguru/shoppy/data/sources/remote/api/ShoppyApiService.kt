package com.danielwaiguru.shoppy.data.sources.remote.api

import com.danielwaiguru.shoppy.data.models.responses.ProductResponse
import com.danielwaiguru.shoppy.data.models.responses.ProductsResponse
import com.danielwaiguru.shoppy.data.sources.remote.utils.ApiEndpoints.PRODUCTS_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Path

interface ShoppyApiService {
    @GET(PRODUCTS_ENDPOINT)
    suspend fun getProducts(): ProductsResponse

    @GET("$PRODUCTS_ENDPOINT/{productId}")
    suspend fun getProductById(
        @Path("productId") productId: Int
    ): ProductResponse
}