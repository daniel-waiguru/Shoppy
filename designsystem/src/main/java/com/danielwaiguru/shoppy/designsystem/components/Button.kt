package com.danielwaiguru.shoppy.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.danielwaiguru.shoppy.designsystem.previews.ShoppyPreviews
import com.danielwaiguru.shoppy.designsystem.theme.ShoppyTheme

@Composable
fun ShoppyPrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    onClick: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(
        top = 15.dp,
        bottom = 15.dp
    )
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .testTag("shoppy_primary_button"),
        shape = shape,
        contentPadding = contentPadding
    ) {
        Text(
            text = text
        )
    }
}

@Composable
fun ShoppySecondaryButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    onClick: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(
        top = 15.dp,
        bottom = 15.dp
    )
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        elevation = null,
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary
        ),
        contentPadding = contentPadding
    ) {
        Text(text = text)
    }
}

@ShoppyPreviews
@Composable
private fun ShoppyPrimaryButtonPreview() {
    ShoppyTheme {
        ShoppyPrimaryButton(
            text = "Click",
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}

@ShoppyPreviews
@Composable
private fun ShoppySecondaryButtonPreview() {
    ShoppyTheme {
        ShoppySecondaryButton(
            text = "Click",
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}