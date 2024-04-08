package ir.codroid.onboarding_presentation.shop_info

interface ShopInfoContract {

    data class State(
        val shopName: String = "",
        val shopDescription: String = "",
        val shopImagePath: String? = null,
    )

    sealed class Event {
        data class OnShopNameChange(val shopName: String) : Event()
        data class OnShopDescriptionChange(val shopDescription: String) : Event()
        data class OnImageChange(val shopImage: String) : Event()

        data object OnNextClick : Event()
    }
}