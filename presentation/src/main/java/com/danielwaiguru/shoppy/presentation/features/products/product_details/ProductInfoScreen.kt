package com.danielwaiguru.shoppy.presentation.features.products.product_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.danielwaiguru.shoppy.designsystem.components.ShoppyAsyncImage
import com.danielwaiguru.shoppy.designsystem.components.ShoppyPrimaryButton
import com.danielwaiguru.shoppy.designsystem.components.ShoppyProgressIndicator
import com.danielwaiguru.shoppy.designsystem.components.ShoppySecondaryButton
import com.danielwaiguru.shoppy.designsystem.components.ShoppyTopAppBar
import com.danielwaiguru.shoppy.designsystem.theme.ShoppyTheme
import com.danielwaiguru.shoppy.domain.models.Product
import com.danielwaiguru.shoppy.presentation.R
import com.danielwaiguru.shoppy.presentation.common.ui.ShoppyErrorView
import com.danielwaiguru.shoppy.presentation.features.products.models.ProductCartUIState
import kotlinx.coroutines.launch

@Composable
fun ProductInfoRoute(
    onNavBack: () -> Unit,
    viewModel: ProductInfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.productUIState.collectAsStateWithLifecycle()
    ProductInfoScreen(
        state = uiState,
        onNavBack = onNavBack,
        onAddQuantity = viewModel::onAddQuantity,
        onSubtractQuantity = viewModel::onSubtractQuantity,
        modifier = Modifier
            .fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductInfoScreen(
    state: ProductCartUIState,
    onNavBack: () -> Unit,
    onAddQuantity: () -> Unit,
    onSubtractQuantity: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = modifier,
        topBar = {
            ShoppyTopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                navigationIcon = Icons.Rounded.ArrowBack,
                onNavIconPressed = onNavBack,
                title = ""
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        when {
            state.product != null && state.isLoading.not() -> {
                ProductInfoSection(
                    product = state.product,
                    cartQuantity = state.cartQuantity,
                    onAddQuantity = onAddQuantity,
                    snackbarHostState = snackbarHostState,
                    onSubtractQuantity = onSubtractQuantity,
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

            state.errorMessage != null -> {
                ShoppyErrorView(
                    description = state.errorMessage,
                    modifier = Modifier
                        .fillMaxSize()

                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductInfoSection(
    product: Product,
    cartQuantity: Int,
    onAddQuantity: () -> Unit,
    onSubtractQuantity: () -> Unit,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.outline
                )
                .fillMaxWidth()
        ) {
            ShoppyAsyncImage(
                url = product.imageLocation,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxHeight(0.50f)
                    .fillMaxWidth()

            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.padding(top = 6.dp)
            )
            SuggestionChip(
                onClick = {},
                label = {
                    Text(
                        text = stringResource(id = R.string.instock_placeholder, product.quantity)
                    )
                },
                colors = SuggestionChipDefaults.suggestionChipColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )

            )
            Divider()
            Text(
                text = stringResource(id = R.string.description_title),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium
            )
            QuantitySection(
                quantity = cartQuantity,
                onAddQuantity = onAddQuantity,
                onSubtractQuantity = onSubtractQuantity,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
            Divider()
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                ShoppySecondaryButton(
                    text = stringResource(id = R.string.add_to_cart),
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "$cartQuantity Added to cart"
                            )
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp)
                )
                ShoppyPrimaryButton(
                    text = stringResource(id = R.string.buy_now),
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "About to buy $cartQuantity items of ${product.name}"
                            )
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
    }
}

@Composable
fun QuantitySection(
    quantity: Int,
    onAddQuantity: () -> Unit,
    onSubtractQuantity: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.quantity),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(end = 12.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.outline,
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            IconButton(onClick = onSubtractQuantity) {
                Icon(
                    imageVector = Icons.Rounded.Remove,
                    contentDescription = stringResource(
                        id = R.string.subtract_quantity_content_desc
                    )
                )
            }
            Text(
                text = quantity.toString(),
                textAlign = TextAlign.Center
            )
            IconButton(onClick = onAddQuantity) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(id = R.string.add_quantity_content_desc)
                )
            }
        }
    }
}

@Preview
@Composable
fun QuantitySectionPreview() {
    ShoppyTheme {
        QuantitySection(
            quantity = 3,
            onAddQuantity = {},
            onSubtractQuantity = {}
        )
    }
}

// @ShoppyPreviews
// @Composable
// fun ProductInfoScreenPreview() {
//    ProductInfoScreen(
//        state = ShoppyUIState(
//            value = Product(
//                currencyCode = "intellegat",
//                description = "eirmod",
//                id = 2573,
//                imageLocation = "https://dev-images-carry1st-products.s3.eu-west-2.amazonaws.com/74e517a3-0615-4019-bb08-cc697cf4e747.png",
//                name = "Russel Wilson",
//                price = 5800,
//                quantity = 8911
//            )
//        ),
//        modifier = Modifier
//            .fillMaxSize(),
//        onNavBack = {}
//    )
// }