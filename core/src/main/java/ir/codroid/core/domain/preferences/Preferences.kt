package ir.codroid.core.domain.preferences

import ir.codroid.core.domain.model.ShopInfo

interface Preferences {

    suspend fun saveShopName(shopName: String)
    suspend fun saveShopDescription(shopDescription: String)
    suspend fun saveShopImage(shopImage: String)

    suspend fun loadShopInfo(): ShopInfo?

}