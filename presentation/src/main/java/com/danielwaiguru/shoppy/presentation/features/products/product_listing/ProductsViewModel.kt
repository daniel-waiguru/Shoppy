package com.danielwaiguru.shoppy.presentation.features.products.product_listing

import androidx.lifecycle.ViewModel
import com.danielwaiguru.shoppy.domain.repositories.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
) : ViewModel() {

}