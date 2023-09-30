package com.danielwaiguru.shoppy.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.danielwaiguru.shoppy.presentation.R
import com.danielwaiguru.shoppy.presentation.utils.PresentationConstants.PRODUCT_ID_ARG_KEY

/**
 * Defines the destinations for this app
 */
interface ShoppyDestinations {
    val route: String
    @get:StringRes
    val title: Int
}
object ProductsScreen: ShoppyDestinations {
    override val route: String
        get() = "com.danielwaiguru.shoppy.presentation.ProductsScreen"
    override val title: Int
        get() = R.string.app_name
}

object ProductInfoScreen: ShoppyDestinations {
    override val route: String
        get() = "com.danielwaiguru.shoppy.presentation.ProductInfoScreen"

    override val title: Int
        get() = R.string.products_info_screen_title
    val routeWithArgs = "$route/{$PRODUCT_ID_ARG_KEY}"
    fun createRoute(productId: Int) = routeWithArgs.replace(
        "{$PRODUCT_ID_ARG_KEY}",
        productId.toString()
    )

    val arguments = listOf(
        navArgument(PRODUCT_ID_ARG_KEY) { type = NavType.IntType }
    )
}