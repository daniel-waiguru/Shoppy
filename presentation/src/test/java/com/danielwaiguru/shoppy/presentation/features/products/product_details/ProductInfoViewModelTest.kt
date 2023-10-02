package com.danielwaiguru.shoppy.presentation.features.products.product_details

import androidx.lifecycle.SavedStateHandle
import com.danielwaiguru.shoppy.domain.utils.ResultWrapper
import com.danielwaiguru.shoppy.testing.base.BaseViewModelTest
import com.danielwaiguru.shoppy.testing.repositories.TestProductsRepository
import com.danielwaiguru.shoppy.testing.test_data.notFoundError
import com.danielwaiguru.shoppy.testing.test_data.testProduct
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProductInfoViewModelTest : BaseViewModelTest() {
    private val productsRepository = TestProductsRepository()
    private lateinit var viewModel: ProductInfoViewModel
    private val savedStateHandle = SavedStateHandle()

    @Before
    fun setup() {
        viewModel = ProductInfoViewModel(
            productsRepository = productsRepository,
            savedStateHandle = savedStateHandle
        )
    }

    @Test
    fun `test success product retrieval returns success UI state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.productUIState.collect()
        }
        viewModel.getProduct(4)
        val uiState = viewModel.productUIState.value
        assertFalse(uiState.isLoading)
        assertNull(uiState.errorMessage)
        Truth.assertThat(uiState.product).isEqualTo(testProduct())
        collectJob.cancel()
    }

    @Test
    fun `test failure product retrieval returns error UI state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.productUIState.collect()
        }
        productsRepository.setProductResult(ResultWrapper.Error(notFoundError))
        viewModel.getProduct(4)
        val uiState = viewModel.productUIState.value
        assertFalse(uiState.isLoading)
        Truth.assertThat(uiState.errorMessage).isEqualTo(notFoundError)
        assertNull(uiState.product)
        collectJob.cancel()
    }

    @Test
    fun `test incrementing cart count updates ui state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.productUIState.collect()
        }
        viewModel.onAddQuantity()
        val uiState = viewModel.productUIState.value
        Truth.assertThat(uiState.cartQuantity).isEqualTo(2)
        collectJob.cancel()
    }

    @Test
    fun `test decrementing cart count updates ui state`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.productUIState.collect()
        }
        viewModel.onAddQuantity()
        viewModel.onAddQuantity()
        viewModel.onSubtractQuantity()
        val uiState = viewModel.productUIState.value
        Truth.assertThat(uiState.cartQuantity).isEqualTo(2)
        collectJob.cancel()
    }

    @Test
    fun `test decrementing cart count cannot reduce below 1`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.productUIState.collect()
        }
        viewModel.onSubtractQuantity()
        viewModel.onSubtractQuantity()
        val uiState = viewModel.productUIState.value
        Truth.assertThat(uiState.cartQuantity).isEqualTo(1)
        collectJob.cancel()
    }
}