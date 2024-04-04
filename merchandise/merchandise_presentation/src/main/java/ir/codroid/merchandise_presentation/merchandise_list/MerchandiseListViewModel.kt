package ir.codroid.merchandise_presentation.merchandise_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.codroid.core.util.UiEvent
import ir.codroid.merchandise_domain.use_case.MerchandiseUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject


@HiltViewModel
class MerchandiseListViewModel @Inject constructor(
    private val merchandiseUseCases: MerchandiseUseCases
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    var state by mutableStateOf(MerchandiseContract.State())
        private set

    init {
        getMerchandise()
    }

    fun onEvent(event: MerchandiseContract.Event) {
        when (event) {
            is MerchandiseContract.Event.GetMerchandise -> {
                getMerchandise()
            }

            is MerchandiseContract.Event.OnAddClick -> {

            }

            is MerchandiseContract.Event.OnMerchandiseClick -> {

            }
        }
    }

    private fun getMerchandise() {
        merchandiseUseCases.getMerchandiseListUseCase.invoke()
            .onEach { merchandiseList ->
                state = state.copy(
                    merchandiseList = merchandiseList
                )
            }
            .launchIn(viewModelScope)
    }
}