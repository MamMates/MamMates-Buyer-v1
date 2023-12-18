package com.mammates.mammates_buyer_v1.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.OrderBuyerItem
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.FoodsSearchItem
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.SearchFoodsDto
import com.mammates.mammates_buyer_v1.domain.model.FoodItem
import com.mammates.mammates_buyer_v1.domain.model.StoreDetail
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH:mm:ss", Locale("id", "ID")).format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"

    return File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
}

fun Map<Int, Int>.toListOrderBuyerItem(): List<OrderBuyerItem> =
    map { OrderBuyerItem(it.key, it.value) }.filter { it.quantity != 0 }

fun FoodsSearchItem.toFoodItem(): FoodItem {
    return FoodItem(
        id = id ?: 0,
        seller = seller?.name ?: "No Data Seller Provides",
        name = name ?: "No Data Food Name Provides",
        rating = when (mamRates) {
            0 -> Rating.ZERO
            1 -> Rating.ONE
            2 -> Rating.TWO
            3 -> Rating.THREE
            else -> Rating.Undefine
        },
        price = price ?: 0
    )
}

fun SearchFoodsDto.toListFoodItem(): List<FoodItem> {
    return foods?.map {
        it.toFoodItem()
    } ?: emptyList()
}

fun SearchFoodsDto.toStoreDetail(): StoreDetail {
    return StoreDetail(
        id = foods?.get(0)?.seller?.id ?: 0,
        seller = foods?.get(0)?.seller?.name ?: "No Data Seller Provides",
        address = foods?.get(0)?.seller?.address ?: "No Data Address Provides",
        foods = toListFoodItem()
    )
}

