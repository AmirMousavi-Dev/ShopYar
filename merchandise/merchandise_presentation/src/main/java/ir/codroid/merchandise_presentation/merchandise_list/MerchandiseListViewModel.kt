package ir.codroid.merchandise_presentation.merchandise_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.codroid.core.util.UiEvent
import ir.codroid.merchandise_domain.use_case.MerchandiseUseCases
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject


@HiltViewModel
@FlowPreview
class MerchandiseListViewModel @Inject constructor(
    private val merchandiseUseCases: MerchandiseUseCases
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    var state by mutableStateOf(MerchandiseContract.State())
        private set


    init {
        getMerchandise(state.searchQuery)
    }

    fun onEvent(event: MerchandiseContract.Event) {
        when (event) {
            is MerchandiseContract.Event.GetMerchandise -> {
                getMerchandise(state.searchQuery)
            }

            is MerchandiseContract.Event.OnActiveSearching -> {
                handleSearching(event.isSearching)
            }

            is MerchandiseContract.Event.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.searchQuery)
                state = state.copy(searchedMerchandiseList = state.merchandiseList.filter {
                    it.doesMatchSearchQuery(event.searchQuery)
                })
            }
        }
    }


    private fun getMerchandise(name: String) {
        merchandiseUseCases.getMerchandiseListUseCase(name)
            .debounce(500L)
            .onEach { merchandiseList ->
                state = state.copy(
                    merchandiseList = merchandiseList
                )
            }
            .launchIn(viewModelScope)
    }


    private fun handleSearching(isSearching: Boolean) {
        state = state.copy(isSearching = isSearching)
        if (!isSearching) {
            state = state.copy(searchedMerchandiseList = emptyList(), searchQuery = "")
        }
    }
}