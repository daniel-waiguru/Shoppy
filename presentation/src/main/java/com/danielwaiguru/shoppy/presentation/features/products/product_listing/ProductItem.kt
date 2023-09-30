package com.danielwaiguru.shoppy.presentation.features.products.product_listing

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.danielwaiguru.shoppy.designsystem.components.ShoppyAsyncImage
import com.danielwaiguru.shoppy.domain.models.Product

@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier
) {
    val priceWithCurrency by remember(product.currencyCode, product.price) {
        mutableStateOf("${product.currencyCode} ${product.price}")
    }
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            ShoppyAsyncImage(
                url = product.imageLocation,
                contentDescription = product.name,
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
            )
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 8.dp)
            )
            Text(
                text = priceWithCurrency,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 8.dp)
            )
        }
    }
}
