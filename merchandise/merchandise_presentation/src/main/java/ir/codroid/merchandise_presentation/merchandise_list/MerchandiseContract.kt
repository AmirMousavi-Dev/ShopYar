package ir.codroid.merchandise_presentation.merchandise_list

import ir.codroid.merchandise_domain.model.Merchandise

interface MerchandiseContract {


    data class State(
        val merchandiseList: List<Merchandise> = emptyList(),
        val searchedMerchandiseList: List<Merchandise> = emptyList(),
        val searchQuery: String = "",
        val isSearching: Boolean = false,

        )


    sealed class Event {
        data object GetMerchandise : Event()

        data class OnSearchQueryChange(val searchQuery: String) : Event()

        data class OnActiveSearching(val isSearching: Boolean) : Event()
    }
}