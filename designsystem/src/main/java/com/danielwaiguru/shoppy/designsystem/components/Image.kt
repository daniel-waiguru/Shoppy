package com.danielwaiguru.shoppy.designsystem.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.danielwaiguru.shoppy.designsystem.R
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

@Composable
fun ShoppyAsyncImage(
    url: String,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(1800)
        .setBaseAlpha(0.7f)
        .setHighlightAlpha(0.6f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()

    val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .placeholder(shimmerDrawable)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .error(R.drawable.no_image_placeholder)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Inside,
        modifier = modifier
            .testTag("shoppy_async_image")
    )
}