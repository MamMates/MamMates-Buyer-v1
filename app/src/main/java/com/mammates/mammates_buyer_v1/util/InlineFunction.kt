package com.mammates.mammates_buyer_v1.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mammates.mammates_buyer_v1.data.source.remote.dto.request.OrderBuyerItem
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.FoodOrderItemDto
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.FoodsSearchItem
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.OrderDetailDto
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.OrderItemDto
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.OrdersDto
import com.mammates.mammates_buyer_v1.data.source.remote.dto.response.SearchFoodsDto
import com.mammates.mammates_buyer_v1.domain.model.FoodOrder
import com.mammates.mammates_buyer_v1.domain.model.FoodRecommendation
import com.mammates.mammates_buyer_v1.domain.model.FoodSearch
import com.mammates.mammates_buyer_v1.domain.model.Order
import com.mammates.mammates_buyer_v1.domain.model.OrderDetail
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

fun FoodsSearchItem.toFoodItem(): FoodSearch {
    return FoodSearch(
        id = id ?: -69,
        seller = seller?.name ?: "No Data Seller Provides",
        name = name ?: "No Data Food Name Provides",
        rating = when (mamRates) {
            0 -> Rating.ZERO
            1 -> Rating.ONE
            2 -> Rating.TWO
            3 -> Rating.THREE
            else -> Rating.Undefine
        },
        image = image,
        price = price ?: 0,
        sellerId = seller?.id ?: -69
    )
}

fun FoodsSearchItem.toFoodRecomendation(): FoodRecommendation {
    return FoodRecommendation(
        id = id ?: -69,
        name = name ?: "No Data Name Provides",
        price = price ?: 0,
        storeId = seller?.id ?: -69,
        image = image
    )
}

fun SearchFoodsDto.toListFoodItem(): List<FoodSearch> {
    return foods?.map {
        it.toFoodItem()
    } ?: emptyList()
}

fun SearchFoodsDto.toStoreDetail(): StoreDetail {
    return StoreDetail(
        id = foods?.get(0)?.seller?.id ?: -69,
        seller = foods?.get(0)?.seller?.name ?: "No Data Seller Provides",
        address = foods?.get(0)?.seller?.address ?: "No Data Address Provides",
        foods = toListFoodItem(),
    )
}

fun SearchFoodsDto.toListFoodRecomendation(): List<FoodRecommendation> {
    return this.foods?.map {
        it.toFoodRecomendation()
    } ?: emptyList()
}

fun FoodOrderItemDto.toFoodOrder(): FoodOrder {
    return FoodOrder(
        image = image,
        quantity = quantity ?: 0,
        price = price ?: 0,
        name = name ?: "No Name Provides",
    )
}

fun OrderItemDto.toOrder(): Order {
    return Order(
        id = id ?: -69,
        total = total ?: 0,
        store = store ?: "No Store Provides",
        status = when (status) {
            0 -> StatusOrder.Canceled
            1 -> StatusOrder.Unconfirmed
            2 -> StatusOrder.Confirmed
            3 -> StatusOrder.Finish
            else -> StatusOrder.Unidentified
        },
        foods = foods?.map {
            it.toFoodOrder()
        } ?: emptyList()
    )
}

fun OrdersDto.toListOrder(): List<Order> {
    return orders?.map {
        Order(
            id = it.id ?: -69,
            total = it.total ?: 0,
            store = it.store ?: "No Store Provides",
            status = when (it.status) {
                0 -> StatusOrder.Canceled
                1 -> StatusOrder.Unconfirmed
                2 -> StatusOrder.Confirmed
                3 -> StatusOrder.Finish
                else -> StatusOrder.Unidentified
            },
            foods = it.foods?.map {
                it.toFoodOrder()
            } ?: emptyList()
        )
    } ?: emptyList()
}


fun OrderDetailDto.toOrderDetail(): OrderDetail {
    return OrderDetail(
        invoice = order?.invoice ?: "No Invoice Provides",
        store = order?.store ?: "No Store Provides",
        date = order?.time ?: "No date provides",
        foods = order?.foods?.map {
            it.toFoodOrder()
        } ?: emptyList(),
        total = order?.total ?: 0,
        status = when (order?.status) {
            0 -> StatusOrder.Canceled
            1 -> StatusOrder.Unconfirmed
            2 -> StatusOrder.Confirmed
            3 -> StatusOrder.Finish
            else -> StatusOrder.Unidentified
        }
    )

}