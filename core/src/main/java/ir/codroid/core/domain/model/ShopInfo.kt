package ir.codroid.core.domain.model

import android.graphics.Bitmap

data class ShopInfo(
    val shopName: String? = "",
    val shopDescription: String? = "",
    val shopImage: Bitmap? = null
)
