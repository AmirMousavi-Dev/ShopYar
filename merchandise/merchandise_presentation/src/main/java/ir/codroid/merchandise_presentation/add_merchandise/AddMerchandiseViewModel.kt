package ir.codroid.merchandise_presentation.add_merchandise

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.codroid.core.util.UiEvent
import ir.codroid.core.util.UiText
import ir.codroid.merchandise_domain.use_case.MerchandiseUseCases
import ir.codroid.merchandise_domain.use_case.ValidateMerchandiseUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMerchandiseViewModel @Inject constructor(
    private val merchandiseUseCases: MerchandiseUseCases
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var state by mutableStateOf(AddMerchandiseContract.State())

    fun onEvent(event: AddMerchandiseContract.Event) {
        when (event) {
            is AddMerchandiseContract.Event.OnCodeChange -> state = state.copy(code = event.code)
            is AddMerchandiseContract.Event.OnCountChange -> state =
                state.copy(count = event.count.toDouble())

            is AddMerchandiseContract.Event.OnCountUnitChange -> state =
                state.copy(countUnit = event.countUnit)

            is AddMerchandiseContract.Event.OnImageChange -> state = state.copy(image = event.image)
            is AddMerchandiseContract.Event.OnNameChange -> state = state.copy(name = event.name)
            is AddMerchandiseContract.Event.OnPurchasePriceChange -> state =
                state.copy(purchasePrice = event.purchasePrice.toInt())

            is AddMerchandiseContract.Event.OnSalesPriceChange -> state =
                state.copy(salesPrice = event.salesPrice.toInt())

            is AddMerchandiseContract.Event.OnAddClick -> {
                val result = merchandiseUseCases.validateMerchandiseUseCase(
                    state.name,
                    state.purchasePrice,
                    state.salesPrice,
                    state.code,
                    state.countUnit,
                    state.image,
                    state.count
                )
                when (result) {
                    is ValidateMerchandiseUseCase.Result.Failure -> {
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.ShowSnackbar(result.uiText))
                        }
                    }

                    is ValidateMerchandiseUseCase.Result.Success -> {
                        viewModelScope.launch {
                            merchandiseUseCases.insertMerchandiseUseCase(result.merchandise)
                                .onSuccess {
                                    _uiEvent.send(UiEvent.Success)
                                }
                                .onFailure {
                                    _uiEvent.send(
                                        UiEvent.ShowSnackbar(
                                            UiText.DynamicString(
                                                it.message ?: it.toString()
                                            )
                                        )
                                    )

                                }
                        }
                    }
                }
            }
        }
    }
}