package com.mammates.mammates_buyer_v1.presentation.pages.main.store.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddRemoveItem(
    quantityValue: Int,
    onAddQuantity: () -> Unit,
    onRemoveQuantity: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        if (quantityValue != 0) {
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                    )
                    .clickable {
                        onRemoveQuantity()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Remove Icon",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }
            Text(
                text = "$quantityValue",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Box(
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                )
                .clickable {
                    onAddQuantity()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon",
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}