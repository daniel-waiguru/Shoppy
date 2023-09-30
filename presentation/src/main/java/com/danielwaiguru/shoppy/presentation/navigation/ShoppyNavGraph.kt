package com.danielwaiguru.shoppy.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.danielwaiguru.shoppy.presentation.features.products.product_details.ProductInfoRoute
import com.danielwaiguru.shoppy.presentation.features.products.product_listing.ProductsRoute

@Composable
fun ShoppyNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = ProductsScreen.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ProductsScreen.route) {
            ProductsRoute(
                onClick = { productId ->
                    navController.navigate(ProductInfoScreen.createRoute(productId))
                }
            )
        }
        composable(ProductInfoScreen.routeWithArgs) {
            ProductInfoRoute(
                onNavBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}