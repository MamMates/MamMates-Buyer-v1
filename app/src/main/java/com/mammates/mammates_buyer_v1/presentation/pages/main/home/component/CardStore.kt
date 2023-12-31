package com.mammates.mammates_buyer_v1.presentation.pages.main.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mammates.mammates_buyer_v1.common.Constants

@Composable
fun CardStore(
    modifier: Modifier = Modifier,
    name: String,
    address: String,
    image: String?,
    onCLick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
            )
            .width(230.dp)
            .clickable {
                onCLick()
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .width(230.dp)
                .height(120.dp),
            model = if (image.isNullOrEmpty()) {
                Constants.DUMMY_PHOTO_FOOD
            } else {
                image
            },
            contentScale = ContentScale.Crop,
            contentDescription = "Food Photo"
        )
        Column(
            modifier = Modifier.padding(
                horizontal = 15.dp,
                vertical = 15.dp
            )
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = address,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
fun CardStorePreview() {
    CardStore(
        name = "Toko Pak Tude",
        address = "Jl. Donut Ubi Mawar Terbang III",
        image = null,
        onCLick = {}
    )
}