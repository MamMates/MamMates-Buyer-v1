package com.mammates.mammates_buyer_v1.presentation.pages.main.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun CardFood(
    modifier: Modifier = Modifier,
    name: String,
    price: Int,
    image: String?,
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .width(110.dp)
                .height(70.dp),
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
                horizontal = 10.dp,
                vertical = 5.dp
            )
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Text(
                text = "Rp. ${price}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
    }
}

@Preview
@Composable
fun CardFoodPreview() {
    CardFood(
        name = "Donut",
        price = 5000,
        image = null
    )
}