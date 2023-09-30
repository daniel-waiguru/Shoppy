package com.danielwaiguru.shoppy.designsystem.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.danielwaiguru.shoppy.designsystem.previews.ShoppyPreviews

@Composable
fun ShoppyProgressIndicator(
    modifier: Modifier = Modifier,
    stockWidth: Dp = 2.dp,
    color: Color = MaterialTheme.colorScheme.primary
) {
    CircularProgressIndicator(
        modifier = modifier
            .testTag("shoppy_progress_indicator"),
        color = color,
        strokeWidth = stockWidth
    )
}

@ShoppyPreviews
@Composable
fun ShoppyProgressIndicatorPreview() {
    ShoppyProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
    )
}