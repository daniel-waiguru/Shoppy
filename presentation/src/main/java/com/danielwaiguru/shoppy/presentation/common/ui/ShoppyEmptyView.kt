package com.danielwaiguru.shoppy.presentation.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.danielwaiguru.shoppy.designsystem.previews.ShoppyPreviews
import com.danielwaiguru.shoppy.presentation.R

@Composable
fun ShoppyEmptyView(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.no_data_found),
    description: String?
) {
    Column(
        modifier = modifier
            .testTag("shoppy_empty_view"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(text = description ?: stringResource(id = R.string.products_will_appear_here))
    }
}

@ShoppyPreviews
@Composable
fun ShoppyEmptyViewPreview() {
    ShoppyEmptyView(
        title = "Error",
        description = "An error occurred",
        modifier = Modifier.fillMaxSize()
    )
}