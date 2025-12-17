package com.danielwaiguru.shoppy.presentation.features.products.product_listing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.danielwaiguru.shoppy.designsystem.components.ShoppyProgressIndicator
import com.danielwaiguru.shoppy.designsystem.components.ShoppyTopAppBar
import com.danielwaiguru.shoppy.designsystem.theme.DarkGrey11
import com.danielwaiguru.shoppy.domain.models.Product
import com.danielwaiguru.shoppy.presentation.R
import com.danielwaiguru.shoppy.presentation.common.ShoppyUIState
import com.danielwaiguru.shoppy.presentation.common.ui.ShoppyEmptyView
import com.danielwaiguru.shoppy.presentation.common.ui.ShoppyErrorView
import com.danielwaiguru.shoppy.presentation.navigation.ProductsScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProductsRoute(
    onClick: (productId: Int) -> Unit,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val uiState by viewModel.productsUIState.collectAsStateWithLifecycle()
    ProductsScreen(
        onClick = onClick,
        state = uiState,
        modifier = Modifier
            .fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    onClick: (productId: Int) -> Unit,
    state: ShoppyUIState<List<Product>>,
    modifier: Modifier = Modifier
) {
    val systemUiController = rememberSystemUiController()
    val useDarIcons = isSystemInDarkTheme().not()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarIcons) Color.White else DarkGrey11,
            darkIcons = useDarIcons
        )
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            ShoppyTopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                title = stringResource(id = ProductsScreen.title)
            )
        }
    ) { paddingValues ->
        when {
            state.isLoading.not() && state.errorMessage != null -> {
                ShoppyErrorView(
                    description = stringResource(id = R.string.error_getting_products),
                    modifier = Modifier
                        .fillMaxSize()

                )
            }
            state.isLoading.not() && state.value.isEmpty() -> {
                ShoppyEmptyView(
                    title = stringResource(id = R.string.no_products_found),
                    description = stringResource(id = R.string.products_will_appear_here),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    ShoppyProgressIndicator(
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.Center),
                        stockWidth = 2.dp
                    )
                }
            }
            else -> {
                ProductsGrid(
                    onClick = onClick,
                    products = state.value,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun ProductsGrid(
    onClick: (productId: Int) -> Unit,
    modifier: Modifier = Modifier,
    products: List<Product>
) {
    LazyVerticalGrid(
        modifier = modifier
            .testTag("products_grid"),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        columns = GridCells.Adaptive(minSize = 128.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products, key = Product::id) { product ->
            val clickableModifier = remember(product) {
                Modifier.clickable { onClick(product.id) }
                    .testTag("product_item")
            }
            ProductItem(
                product = product,
                modifier = clickableModifier
            )
        }
    }
}