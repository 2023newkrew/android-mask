package com.survivalcoding.maskinfo.presentation.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.survivalcoding.maskinfo.domain.model.Photo

@Composable
fun PhotoItem(photo: Photo) {
    AsyncImage(
        model = photo.url,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .aspectRatio(1f)
    )
}