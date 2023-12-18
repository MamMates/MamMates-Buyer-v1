package com.mammates.mammates_buyer_v1.presentation.pages.main.order_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mammates.mammates_buyer_v1.presentation.component.text.TextLabelValue
import com.mammates.mammates_buyer_v1.presentation.pages.main.order_detail.component.CardOrderFood
import com.mammates.mammates_buyer_v1.util.StatusOrder

@Composable
fun OrderDetailScreen() {

    val dummyList = (1..3).toList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp),
    ) {
        LazyColumn {
            item {
                Text(
                    text = "See Your Order",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(35.dp))
                TextLabelValue(
                    label = "Store",
                    value = "Toko Pak Tude"
                )
                TextLabelValue(
                    label = "Date",
                    value = "12 Agustus 2023"
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(dummyList) {
                CardOrderFood(
                    foodName = "Donut Kentang rasa coklat",
                    quantity = 4,
                    image = null,
                    price = 5000
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                TextLabelValue(
                    label = "Total",
                    value = "Rp. 50000"
                )
                TextLabelValue(
                    label = "Payment Method",
                    value = "Cash On Delivery"
                )
                TextLabelValue(
                    label = "Status",
                    statusOrder = StatusOrder.Unconfirmed
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailScreenPreview() {
    OrderDetailScreen()
}