package com.mammates.mammates_buyer_v1.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mammates.mammates_buyer_v1.data.source.remote.dto.OrderBuyerItem
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

