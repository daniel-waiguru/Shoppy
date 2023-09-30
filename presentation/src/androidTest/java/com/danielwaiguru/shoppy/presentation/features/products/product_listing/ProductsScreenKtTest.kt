package com.danielwaiguru.shoppy.presentation.features.products.product_listing

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.danielwaiguru.shoppy.presentation.common.ShoppyUIState
import com.danielwaiguru.shoppy.testing.test_data.notFoundError
import com.danielwaiguru.shoppy.testing.test_data.testProduct
import org.junit.Rule
import org.junit.Test

class ProductsScreenKtTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun loading_state_is_handled_by_displaying_progress_indicator() {
        rule.setContent {
            ProductsScreen(
                state = ShoppyUIState(
                    isLoading = true, value = emptyList()
                ),
                onClick = {}
            )
        }
        rule.onNodeWithTag("shoppy_progress_indicator").assertIsDisplayed()
    }

    @Test
    fun error_state_is_handled_by_displaying_error_view() {
        rule.setContent {
            ProductsScreen(
                state = ShoppyUIState(
                    errorMessage = notFoundError,
                    value = emptyList()
                ),
                onClick = {}
            )
        }
        rule.onNodeWithTag("shoppy_error_view").assertIsDisplayed()
    }
    @Test
    fun success_state_is_handled_by_displaying_product_grid() {
        rule.setContent {
            ProductsScreen(
                state = ShoppyUIState(
                    value = listOf(
                        testProduct()
                    )
                ),
                onClick = {}
            )
        }
        rule.onNodeWithTag("products_grid").assertIsDisplayed()
    }

    @Test
    fun test_product_has_click_actions() {
        rule.setContent {
            ProductsScreen(
                state = ShoppyUIState(
                    value = listOf(
                        testProduct()
                    )
                ),
                onClick = {}
            )
        }
        rule.onNodeWithTag("product_item").assertHasClickAction()
    }

}