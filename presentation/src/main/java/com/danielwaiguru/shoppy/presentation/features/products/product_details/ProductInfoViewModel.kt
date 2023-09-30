package com.danielwaiguru.shoppy.presentation.features.products.product_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielwaiguru.shoppy.domain.models.Product
import com.danielwaiguru.shoppy.domain.repositories.ProductsRepository
import com.danielwaiguru.shoppy.domain.utils.ResultWrapper
import com.danielwaiguru.shoppy.presentation.common.ShoppyUIState
import com.danielwaiguru.shoppy.presentation.features.products.models.ProductCartUIState
import com.danielwaiguru.shoppy.presentation.utils.PresentationConstants.PRODUCT_ID_ARG_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _productUIState: MutableStateFlow<ProductCartUIState> = MutableStateFlow(
        ProductCartUIState()
    )
    val productUIState: StateFlow<ProductCartUIState> = _productUIState.asStateFlow()

    init {
        savedStateHandle.get<String>(PRODUCT_ID_ARG_KEY)?.let { productId ->
            getProduct(productId.toInt())
        }
    }

    private fun getProduct(productId: Int) {
        viewModelScope.launch {
            val result = productsRepository.getProduct(productId)
            _productUIState.update { currentState ->
                when(result) {
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
                        product = result.value
                    )
                }
            }
        }
    }

    fun onAddQuantity() {
        _productUIState.update { currentState ->
            currentState.copy(
                cartQuantity = currentState.cartQuantity.plus(1)
            )
        }
    }

    fun onSubtractQuantity() {
        _productUIState.update { currentState ->
            if (currentState.cartQuantity <= 1) return
            currentState.copy(
                cartQuantity = currentState.cartQuantity.minus(1)
            )
        }
    }
}