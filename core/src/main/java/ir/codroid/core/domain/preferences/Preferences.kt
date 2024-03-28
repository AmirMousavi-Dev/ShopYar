package ir.codroid.core.domain.preferences

import android.graphics.Bitmap
import ir.codroid.core.domain.model.ShopInfo

interface Preferences {

    suspend fun saveShopName(shopName: String)
    suspend fun saveShopDescription(shopDescription: String)
    suspend fun saveShopImage(shopImage: Bitmap)

    suspend fun loadShopInfo(): ShopInfo?

}