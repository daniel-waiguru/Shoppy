package com.danielwaiguru.shoppy.presentation.features.products.product_listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielwaiguru.shoppy.domain.models.Product
import com.danielwaiguru.shoppy.domain.repositories.ProductsRepository
import com.danielwaiguru.shoppy.domain.utils.ResultWrapper
import com.danielwaiguru.shoppy.presentation.common.ShoppyUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {
    val productsUIState: StateFlow<ShoppyUIState<List<Product>>> = productsRepository
        .getProducts()
        .map { result ->
            when (result) {
                is ResultWrapper.Error -> ShoppyUIState(
                    isLoading = false,
                    errorMessage = result.errorMessage,
                    value = emptyList()
                )
                ResultWrapper.Loading -> ShoppyUIState(
                    isLoading = true,
                    errorMessage = null,
                    value = emptyList()
                )
                is ResultWrapper.Success -> ShoppyUIState(
                    isLoading = false,
                    errorMessage = null,
                    value = result.value
                )
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ShoppyUIState(value = emptyList(), isLoading = true))
}