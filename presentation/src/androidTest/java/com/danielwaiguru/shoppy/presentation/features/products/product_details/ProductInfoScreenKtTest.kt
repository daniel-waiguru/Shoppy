package com.danielwaiguru.shoppy.presentation.features.products.product_details

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.danielwaiguru.shoppy.presentation.features.products.models.ProductCartUIState
import com.danielwaiguru.shoppy.testing.test_data.notFoundError
import com.danielwaiguru.shoppy.testing.test_data.testProduct
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ProductInfoScreenKtTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun loading_state_is_handled_by_displaying_progress_indicator() {
        rule.setContent {
            ProductInfoScreen(
                state = ProductCartUIState(isLoading = true),
                onNavBack = {  },
                onAddQuantity = { },
                onSubtractQuantity = {  })
        }
        rule.onNodeWithTag("shoppy_progress_indicator").assertIsDisplayed()
    }
    @Test
    fun error_state_is_handled_by_displaying_error_view() {
        rule.setContent {
            ProductInfoScreen(
                state = ProductCartUIState(errorMessage = notFoundError),
                onNavBack = {  },
                onAddQuantity = { },
                onSubtractQuantity = {  })
        }
        rule.onNodeWithTag("shoppy_error_view").assertIsDisplayed()
        rule.onNodeWithTag("error_description").assertTextEquals(notFoundError)
    }

    @Test
    fun success_state_is_handled_by_displaying_error_view() {
        rule.setContent {
            ProductInfoScreen(
                state = ProductCartUIState(
                    product = testProduct()
                ),
                onNavBack = {  },
                onAddQuantity = { },
                onSubtractQuantity = {  })
        }
        rule.onNodeWithText("Robin Sykes").assertIsDisplayed()
    }
}