package com.danielwaiguru.shoppy.domain.repositories

import com.danielwaiguru.shoppy.domain.models.Product
import com.danielwaiguru.shoppy.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    /**
     * Get products
     *
     * @return a list of [Product]
     */
    fun getProducts(): Flow<ResultWrapper<List<Product>>>

    /**
     * Get a product by id
     *
     * @param productId
     * @return [Product]
     */
    suspend fun getProduct(productId: Int): ResultWrapper<Product>
}