package com.vintech.chat.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedItem(item: String) {
    var isItemAdded by remember { mutableStateOf(false) }
    AnimatedContent(
        targetState = isItemAdded,
        transitionSpec = {
            fadeIn(animationSpec = tween(durationMillis = 500)).with(
                fadeOut(
                    animationSpec = tween(
                        durationMillis = 300
                    )
                )
            )
        }
    ) { targetState ->
        if (targetState) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = item)
            }
        }
    }

    LaunchedEffect(item) {
        isItemAdded = true
    }
}

@Preview
@Composable
fun AnimatedItemPreview() {
    AnimatedItem(item = "TODO")
}