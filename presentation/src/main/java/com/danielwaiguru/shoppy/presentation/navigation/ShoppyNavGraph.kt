package com.danielwaiguru.shoppy.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
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
    val enterTransitionAnimation = slideInHorizontally (
        animationSpec = tween(700),
        initialOffsetX = { it }
    ) + fadeIn()

    val exitTransitionAnimation = slideOutVertically(
        animationSpec = tween(700),
        targetOffsetY = { it }
    ) + fadeOut(
        animationSpec = tween(400)
    )
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            ProductsScreen.route,
            enterTransition = { enterTransitionAnimation },
            exitTransition = { exitTransitionAnimation }
        ) {
            ProductsRoute(
                onClick = { productId ->
                    navController.navigate(ProductInfoScreen.createRoute(productId))
                }
            )
        }
        composable(
            ProductInfoScreen.routeWithArgs,
            enterTransition = { enterTransitionAnimation },
            exitTransition = { exitTransitionAnimation }
        ) {
            ProductInfoRoute(
                onNavBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}