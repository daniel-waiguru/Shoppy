package com.danielwaiguru.shoppy.testing.repositories

import com.danielwaiguru.shoppy.domain.models.Product
import com.danielwaiguru.shoppy.domain.repositories.ProductsRepository
import com.danielwaiguru.shoppy.domain.utils.ResultWrapper
import com.danielwaiguru.shoppy.testing.test_data.testProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class TestProductsRepository : ProductsRepository {
    private val productsResult = MutableStateFlow<ResultWrapper<List<Product>>>(
        ResultWrapper.Success(
            listOf(
                testProduct(),
                testProduct(id = 1, quantity = 89, price = 1000)
            )
        )
    )
    private val productResult = MutableStateFlow<ResultWrapper<Product>>(
        ResultWrapper.Success(testProduct())
    )
    override fun getProducts(): Flow<ResultWrapper<List<Product>>> {
        return productsResult
    }

    override suspend fun getProduct(productId: Int): ResultWrapper<Product> {
        return productResult.value
    }

    fun setProductsResult(result: ResultWrapper<List<Product>>) {
        productsResult.update { result }
    }
    fun setProductResult(result: ResultWrapper<Product>) {
        productResult.update { result }
    }
}