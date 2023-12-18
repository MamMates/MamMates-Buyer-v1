package com.mammates.mammates_buyer_v1.presentation.pages.main.order_confirm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmOrderScreen() {

    val dummyList = (1..10).toList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp),
    ) {
        LazyColumn{
            items(dummyList){

            }
        }
    }
}