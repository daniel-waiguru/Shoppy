package com.danielwaiguru.shoppy.presentation.features.products.product_details

import androidx.lifecycle.ViewModel
import com.danielwaiguru.shoppy.domain.repositories.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductInfoViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

}