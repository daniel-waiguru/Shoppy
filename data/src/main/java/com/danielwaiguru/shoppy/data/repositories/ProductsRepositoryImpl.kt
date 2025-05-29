package com.danielwaiguru.shoppy.data.repositories

import com.danielwaiguru.shoppy.data.mappers.toProduct
import com.danielwaiguru.shoppy.data.models.dtos.ProductDto
import com.danielwaiguru.shoppy.data.sources.remote.RemoteDataSource
import com.danielwaiguru.shoppy.data.sources.remote.utils.flowSafeCall
import com.danielwaiguru.shoppy.data.sources.remote.utils.safeApiCall
import com.danielwaiguru.shoppy.domain.models.Product
import com.danielwaiguru.shoppy.domain.repositories.ProductsRepository
import com.danielwaiguru.shoppy.domain.utils.Dispatcher
import com.danielwaiguru.shoppy.domain.utils.DispatcherProvider
import com.danielwaiguru.shoppy.domain.utils.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ProductsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    @Dispatcher(DispatcherProvider.IO) private val ioDispatcher: CoroutineDispatcher
) : ProductsRepository {
    override fun getProducts(): Flow<ResultWrapper<List<Product>>> = flowSafeCall {
        remoteDataSource.getProducts()
            .map(ProductDto::toProduct)
    }

    override suspend fun getProduct(productId: Int): ResultWrapper<Product> =
        safeApiCall(ioDispatcher) {
            remoteDataSource.getProductById(productId).toProduct()
        }
}