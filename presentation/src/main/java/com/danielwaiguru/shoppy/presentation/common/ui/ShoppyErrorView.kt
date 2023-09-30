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
fun ShoppyErrorView(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.error_title),
    description: String?
) {
    Column(
        modifier = modifier
            .testTag("shoppy_error_view"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = description ?: stringResource(
                id = R.string.an_error_occured
            ),
            modifier = Modifier.testTag("error_description")
        )
    }
}

@ShoppyPreviews
@Composable
fun ShoppyErrorViewPreview() {
    ShoppyErrorView(
        title = "Error",
        description = "An error occurred",
        modifier = Modifier.fillMaxSize()
    )
}