package ir.codroid.merchandise_presentation.merchandise_list

import ir.codroid.merchandise_domain.model.Merchandise

interface MerchandiseContract {


    data class State(
        val merchandiseList: List<Merchandise> = emptyList()
    )


    sealed class Event {
        data object GetMerchandise : Event()

        data object OnAddClick : Event()

        data class OnMerchandiseClick(val id: Int) : Event()
    }
}