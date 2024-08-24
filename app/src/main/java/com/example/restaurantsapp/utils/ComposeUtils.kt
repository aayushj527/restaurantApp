package com.example.restaurantsapp.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.bounceClick(
    onClick: () -> Unit
) = composed {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scaleDown = 0.9f

    val animatable = remember {
        Animatable(1f)
    }

    LaunchedEffect(key1 = pressed) {
        if (pressed) {
            animatable.animateTo(scaleDown)
        } else {
            animatable.animateTo(1f)
        }
    }

    graphicsLayer {
        val scale = animatable.value
        scaleX = scale
        scaleY = scale
    }.clickable(
        interactionSource = interactionSource,
        indication = null
    ) {
        onClick()
    }
}