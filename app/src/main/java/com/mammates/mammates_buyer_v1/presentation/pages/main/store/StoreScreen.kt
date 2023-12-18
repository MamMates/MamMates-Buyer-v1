package com.mammates.mammates_buyer_v1.presentation.pages.main.store

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mammates.mammates_buyer_v1.presentation.component.text.TextLabelValue
import com.mammates.mammates_buyer_v1.presentation.pages.main.store.component.CardFoodStore

@Composable
fun StoreScreen(
    navController: NavController,
    state: StoreState,
    onEvent: (StoreEvent) -> Unit
) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp),
    ) {
        LazyColumn(
            modifier = Modifier.padding(bottom = 70.dp),
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Pecel Lele Bro Waw Murah Meriah",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "Location Icons",
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Jalan kampus merdeka 21 gang 69",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
                Spacer(modifier = Modifier.height(25.dp))
            }
            items(state.foods, key = { it.id }) { item ->
                CardFoodStore(
                    foodName = item.name,
                    rating = item.rating,
                    price = item.price,
                    image = item.image,
                    quantityValue = state.quantity.getOrElse(item.id) {
                        onEvent(StoreEvent.PutMapQuantity(item.id))
                        0
                    },
                    onAddQuantity = {
                        onEvent(StoreEvent.OnAddQuantity(item.id))
                    },
                    onRemoveQuantity = {
                        onEvent(StoreEvent.OnRemoveQuantity(item.id))
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(vertical = 15.dp)

        ) {
            Column {
                TextLabelValue(
                    label = "Total:",
                    value = "Rp. ${state.total}"
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        onEvent(StoreEvent.OnSubmitOrder)
                    }
                ) {
                    Text(text = "Buy")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoreScreenPreview() {
    StoreScreen(
        navController = rememberNavController(),
        state = StoreState(),
        onEvent = {}
    )
}