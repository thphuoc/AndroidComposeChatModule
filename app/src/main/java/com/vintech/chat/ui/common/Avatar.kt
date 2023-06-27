package com.vintech.chat.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.vintech.chat.ui.theme.baseSizeX11
import com.vintech.chat.ui.theme.baseSizeX8

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AvatarImage(path: String, size: Dp, modifier: Modifier) {
    GlideImage(
        model = path,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(CircleShape)
            .size(size)
            .border(width = 2f.dp, color = Color.White, shape = CircleShape)
            .then(modifier),
        contentDescription = "LoadImage"
    )
}

@Composable
fun AvatarImageSmall(path: String, modifier: Modifier = Modifier) {
    AvatarImage(path, baseSizeX8, modifier)
}

@Composable
fun AvatarImageMedium(path: String, modifier: Modifier = Modifier) {
    AvatarImage(path, baseSizeX11, modifier)
}

@Composable
fun OnlineStatus(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(15f.dp)
            .clip(CircleShape)
            .background(Color.Green)
            .border(width = 3f.dp, color = Color.White, shape = CircleShape).then(modifier)
    )
}