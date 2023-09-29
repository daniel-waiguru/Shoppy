package com.danielwaiguru.shoppy.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * Defines the destinations for this app
 */
interface ShoppyDestinations {
    val route: String
}
object ProductsScreen: ShoppyDestinations {
    override val route: String
        get() = "com.danielwaiguru.shoppy.presentation.ProductsScreen"
}

object ProductInfoScreen: ShoppyDestinations {
    override val route: String
        get() = "com.danielwaiguru.shoppy.presentation.ProductInfoScreen"
    private const val productIdArg = "productId"
    private val routeWithArgs = "$route/{$productIdArg}"
    fun createRoute(productId: Int) = routeWithArgs.replace(
        "{$productIdArg}",
        productId.toString()
    )

    val arguments = listOf(
        navArgument(productIdArg) { type = NavType.IntType }
    )
}