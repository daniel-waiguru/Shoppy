package com.danielwaiguru.shoppy.presentation.features.products.product_listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielwaiguru.shoppy.domain.models.Product
import com.danielwaiguru.shoppy.domain.repositories.ProductsRepository
import com.danielwaiguru.shoppy.domain.utils.ResultWrapper
import com.danielwaiguru.shoppy.presentation.common.ShoppyUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {
    private val _productsUIState: MutableStateFlow<ShoppyUIState<List<Product>>> = MutableStateFlow(
        ShoppyUIState(value = emptyList())
    )
    val productsUIState: StateFlow<ShoppyUIState<List<Product>>> = _productsUIState.asStateFlow()
    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            productsRepository.getProducts()
                .collect { result ->
                    _productsUIState.update { currentState ->
                        when (result) {
                            is ResultWrapper.Error -> currentState.copy(
                                isLoading = false,
                                errorMessage = result.errorMessage
                            )
                            ResultWrapper.Loading -> currentState.copy(
                                isLoading = true,
                                errorMessage = null
                            )
                            is ResultWrapper.Success -> currentState.copy(
                                isLoading = false,
                                errorMessage = null,
                                value = result.value
                            )
                        }
                    }
                }
        }
    }
}