package ir.codroid.merchandise_presentation.add_merchandise

import android.graphics.Bitmap
import ir.codroid.core.util.UiText
import ir.codroid.merchandise_domain.model.CountUnit
import ir.codroid.merchandise_domain.model.Merchandise
import ir.codroid.core.R

interface AddMerchandiseContract {

    data class State(
        val merchandise: Merchandise = Merchandise(
            name = "",
            purchasePrice = -1,
            salesPrice = -1,
            code = "",
            countUnit = CountUnit.NUMBER,
            image = null,
            count = -1.0,
        ),
        val title : UiText.StringResource = UiText.StringResource(R.string.til_add_merchandise)
    )

    sealed class Event {

        data class OnMerchandiseItemId(val merchandiseItemId: Int) : Event()
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