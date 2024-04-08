package ir.codroid.merchandise_presentation.add_merchandise

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.codroid.core.R
import ir.codroid.core.domain.usecase.SavePhotoToStorageUseCase
import ir.codroid.core.util.UiEvent
import ir.codroid.core.util.UiText
import ir.codroid.merchandise_domain.use_case.MerchandiseUseCases
import ir.codroid.merchandise_domain.use_case.ValidateMerchandiseUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMerchandiseViewModel @Inject constructor(
    private val merchandiseUseCases: MerchandiseUseCases,
    private val savePhotoToStorageUseCase: SavePhotoToStorageUseCase
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var state by mutableStateOf(AddMerchandiseContract.State())

    fun onEvent(event: AddMerchandiseContract.Event) {
        when (event) {
            is AddMerchandiseContract.Event.OnCodeChange -> state =
                state.copy(merchandise = state.merchandise.copy(code = event.code))

            is AddMerchandiseContract.Event.OnCountChange -> state =
                state.copy(
                    merchandise = state.merchandise.copy(
                        count = if (event.count.isNotEmpty()) event.count.toDouble() else 0.0
                    )
                )

            is AddMerchandiseContract.Event.OnCountUnitChange -> state =
                state.copy(merchandise = state.merchandise.copy(countUnit = event.countUnit))

            is AddMerchandiseContract.Event.OnImageChange -> state =
                state.copy(merchandise = state.merchandise.copy(image = event.image))

            is AddMerchandiseContract.Event.OnNameChange -> state =
                state.copy(merchandise = state.merchandise.copy(name = event.name))

            is AddMerchandiseContract.Event.OnPurchasePriceChange -> state =
                state.copy(
                    merchandise = state.merchandise.copy(
                        purchasePrice = if (event.purchasePrice.isNotEmpty()) event.purchasePrice.toInt() else 0
                    )
                )

            is AddMerchandiseContract.Event.OnSalesPriceChange -> state =
                state.copy(
                    merchandise = state.merchandise.copy(
                        salesPrice = if (event.salesPrice.isNotEmpty()) event.salesPrice.toInt() else 0
                    )
                )

            is AddMerchandiseContract.Event.OnAddClick -> {
                val result = merchandiseUseCases.validateMerchandiseUseCase(
                    state.merchandise
                )
                when (result) {
                    is ValidateMerchandiseUseCase.Result.Failure -> {
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.ShowSnackbar(result.uiText))
                        }
                    }

                    is ValidateMerchandiseUseCase.Result.Success -> {
                        state.merchandise.image?.let {

                            val uri = savePhotoToStorageUseCase.invoke(
                                state.merchandise.name + state.merchandise.code,
                                Uri.parse(it)
                            )
                            state.copy(merchandise = state.merchandise.copy(image = uri.toString()))
                        }
                        viewModelScope.launch {
                            merchandiseUseCases.insertMerchandiseUseCase(state.merchandise)
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

            is AddMerchandiseContract.Event.OnMerchandiseItemId -> {
                if (event.merchandiseItemId > 0) {
                    state = state.copy(title = UiText.StringResource(R.string.til_edit_merchandise))
                    getMerchandise(event.merchandiseItemId)
                }
            }
        }
    }


    private fun getMerchandise(id: Int) {
        merchandiseUseCases
            .getMerchandiseUseCase(id)
            .onEach { state = state.copy(merchandise = it) }
            .launchIn(viewModelScope)
    }


}