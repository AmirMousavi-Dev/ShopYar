package ir.codroid.onboarding_presentation.shop_info

import android.graphics.Bitmap

interface ShopInfoContract {

    data class State(
        val shopName: String = "",
        val shopDescription: String = "",
        val shopImage: Bitmap? = null,
    )

    sealed class Event {
        data class OnShopNameChange(val shopName: String) : Event()
        data class OnShopDescriptionChange(val shopDescription: String) : Event()
        data class OnImageChange(val shopImage: Bitmap) : Event()

        data object OnNextClick : Event()
    }
}