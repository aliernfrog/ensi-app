package com.aliernfrog.ensicord.util.extension

import androidx.compose.foundation.clickable
import androidx.compose.material3.ripple
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

fun Modifier.clickableWithColor(
    color: Color,
    onClick: () -> Unit
): Modifier {
    return this.clickable(
        interactionSource = null,
        indication = ripple(color = color),
        onClick = onClick
    )
}