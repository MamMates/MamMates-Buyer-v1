package com.mammates.mammates_buyer_v1.presentation.pages.main.store.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mammates.mammates_buyer_v1.common.Constants
import com.mammates.mammates_buyer_v1.presentation.component.rating.RatingDisplay
import com.mammates.mammates_buyer_v1.util.Rating

@Composable
fun CardFoodStore(
    modifier: Modifier = Modifier,
    foodName: String,
    rating: Rating,
    price: Int,
    image: String?,
    quantityValue: Int,
    onAddQuantity: () -> Unit,
    onRemoveQuantity: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .width(60.dp)
                .height(60.dp),
            model = if (!image.isNullOrEmpty()) {
                image
            } else {
                Constants.DUMMY_PHOTO_FOOD
            },
            contentDescription = "Food Thumbnail",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            Text(
                text = foodName,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            RatingDisplay(rating = rating)
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Rp. $price",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                AddRemoveItem(
                    quantityValue = quantityValue,
                    onAddQuantity = onAddQuantity,
                    onRemoveQuantity = onRemoveQuantity,
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun CardFoodStorePreview() {
    CardFoodStore(
        rating = Rating.TWO,
        foodName = "Donut Keju Suka Terbang",
        price = 5000,
        image = "",
        quantityValue = 0,
        onAddQuantity = {},
        onRemoveQuantity = {}
    )
}