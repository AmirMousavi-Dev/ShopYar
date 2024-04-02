package ir.codroid.merchandise_presentation.add_merchandise

import android.graphics.Bitmap
import ir.codroid.merchandise_domain.model.CountUnit

interface AddMerchandiseContract {

    data class State(
        val name: String = "",
        val purchasePrice: Int? = null,
        val salesPrice: Int? = null,
        val code: String = "",
        val countUnit: CountUnit = CountUnit.NUMBER,
        val image: Bitmap? = null,
        val count: Double? = null,
    )

    sealed class Event {
        data class OnNameChange(val name: String) : Event()
        data class OnPurchasePriceChange(val purchasePrice: String) : Event()
        data class OnSalesPriceChange(val salesPrice: String) : Event()
        data class OnCodeChange(val code: String) : Event()
        data class OnCountUnitChange(val countUnit: CountUnit) : Event()
        data class OnImageChange(val image: Bitmap?) : Event()
        data class OnCountChange(val count: String) : Event()
        data object OnAddClick : Event()
    }
}