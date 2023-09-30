package com.danielwaiguru.shoppy.data.repositories

import app.cash.turbine.test
import com.danielwaiguru.shoppy.data.models.responses.ProductsResponse
import com.danielwaiguru.shoppy.data.sources.remote.RemoteDataSource
import com.danielwaiguru.shoppy.data.test_data.fakeProductResponse
import com.danielwaiguru.shoppy.data.test_data.fakeProductsResponse
import com.danielwaiguru.shoppy.domain.repositories.ProductsRepository
import com.danielwaiguru.shoppy.domain.utils.ResultWrapper
import com.danielwaiguru.shoppy.testing.test_data.notFoundError
import com.danielwaiguru.shoppy.testing.test_data.testProductDto
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class ProductsRepositoryTest {
    @MockK
    val remoteDataSource = mockk<RemoteDataSource>()
    private lateinit var productsRepository: ProductsRepository

    @Before
    fun setup() {
        productsRepository = ProductsRepositoryImpl(
            remoteDataSource = remoteDataSource,
            ioDispatcher = UnconfinedTestDispatcher()
        )
    }
    @Test
    fun `test network request trigger loading state first`() {
        coEvery {
            remoteDataSource.getProducts()
        } returns fakeProductsResponse
        runTest {
            productsRepository.getProducts()
                .test {
                    awaitItem().also { result ->
                        Truth.assertThat(result).isInstanceOf(ResultWrapper.Loading::class.java)
                    }
                    cancelAndIgnoreRemainingEvents()
                }
        }
    }

    @Test
    fun `test retrieving all products returns correctly mapped data`() {
        coEvery {
            remoteDataSource.getProducts()
        } returns fakeProductsResponse

        runTest {
            productsRepository.getProducts()
                .drop(1)
                .test {
                    awaitItem().also { result ->
                        Truth.assertThat(result).isInstanceOf(ResultWrapper.Success::class.java)
                        Truth.assertThat((result as ResultWrapper.Success).value).isNotEmpty()
                        Truth.assertThat(result.value).hasSize(fakeProductsResponse.size)
                    }
                    awaitComplete()
                }
        }
    }
    @Test
    fun `test retrieving all products returns correctly mapped error`() {
        coEvery {
            remoteDataSource.getProducts()
        } throws HttpException(
            Response.error<ProductsResponse>(
                404,
                "{\"message\":\"$notFoundError\"}"
                    .toResponseBody("application/json".toMediaType())
            )
        )
        runTest {
            productsRepository.getProducts()
                .drop(1)
                .test {
                    awaitItem().also { result ->
                        Truth.assertThat(result).isInstanceOf(ResultWrapper.Error::class.java)
                    }
                    awaitComplete()
                }
        }
    }

    @Test
    fun `test retrieving one products returns correctly mapped data`() {
        //Given
        coEvery {
            remoteDataSource.getProductById(any())
        } returns fakeProductResponse

        runTest {
            // When
            val result = productsRepository.getProduct(1)
            //Then
            Truth.assertThat(result).isInstanceOf(ResultWrapper.Success::class.java)
            Truth.assertThat((result as ResultWrapper.Success).value.id)
                .isEqualTo(testProductDto().id)
        }
    }

    @Test
    fun `test getProductById handles error correctly`() {
        //Given
        coEvery {
            remoteDataSource.getProductById(any())
        } throws HttpException(
            Response.error<ProductsResponse>(
                404,
                "{\"message\":\"$notFoundError\"}"
                    .toResponseBody("application/json".toMediaType())
            )
        )

        runTest {
            // When
            val result = productsRepository.getProduct(1)
            //Then
            Truth.assertThat(result).isInstanceOf(ResultWrapper.Error::class.java)
        }
    }
}