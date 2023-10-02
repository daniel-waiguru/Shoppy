package com.danielwaiguru.shoppy.presentation.features.products.product_listing

import app.cash.turbine.test
import com.danielwaiguru.shoppy.domain.utils.ResultWrapper
import com.danielwaiguru.shoppy.testing.base.BaseViewModelTest
import com.danielwaiguru.shoppy.testing.repositories.TestProductsRepository
import com.danielwaiguru.shoppy.testing.test_data.notFoundError
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProductsViewModelTest : BaseViewModelTest() {
    private val productsRepository = TestProductsRepository()
    private lateinit var viewModel: ProductsViewModel

    @Before
    fun setup() {
        viewModel = ProductsViewModel(
            productsRepository = productsRepository
        )
    }

    @Test
    fun `test success products retrieval yields correct success state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.productsUIState.collect()
        }
        viewModel.getProducts()

        val uiState = viewModel.productsUIState.value
        assertFalse(uiState.isLoading)
        kotlin.test.assertNull(uiState.errorMessage)
        Truth.assertThat(uiState.value.size).isEqualTo(2)
        collectJob.cancel()
    }

    @Test
    fun `test get products failure yields correct error state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.productsUIState.collect()
        }
        productsRepository.setProductsResult(ResultWrapper.Error(notFoundError))

        val uiState = viewModel.productsUIState.value
        assertFalse(uiState.isLoading)
        kotlin.test.assertNotNull(uiState.errorMessage)
        Truth.assertThat(uiState.errorMessage).isEqualTo(notFoundError)
        collectJob.cancel()
    }
}