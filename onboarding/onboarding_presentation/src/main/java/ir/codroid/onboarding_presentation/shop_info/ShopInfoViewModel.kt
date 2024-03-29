package ir.codroid.onboarding_presentation.shop_info

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.codroid.core.domain.preferences.Preferences
import ir.codroid.core.util.UiEvent
import ir.codroid.onboarding_domain.use_case.ValidateShopInfoUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopInfoViewModel @Inject constructor(
    private val preferences: Preferences,
    private val validateShopInfoUseCase: ValidateShopInfoUseCase
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var state by mutableStateOf(ShopInfoContract.State())
        private set

    fun onEvent(event: ShopInfoContract.Event) {
        when (event) {
            is ShopInfoContract.Event.OnImageChange -> state =
                state.copy(shopImage = event.shopImage)

            is ShopInfoContract.Event.OnShopDescriptionChange -> state =
                state.copy(shopDescription = event.shopDescription.take(128))

            is ShopInfoContract.Event.OnShopNameChange -> state =
                state.copy(shopName = event.shopName.take(40))

            ShopInfoContract.Event.OnNextClick -> {
                when (val result = validateShopInfoUseCase(state.shopName, state.shopDescription)) {
                    is ValidateShopInfoUseCase.Result.Success -> {
                        viewModelScope.launch {
                            preferences.saveShopName(state.shopName)
                            preferences.saveShopDescription(state.shopDescription)
                            state.shopImage?.let { preferences.saveShopImage(it) }
                            _uiEvent.send(UiEvent.Success)
                        }
                    }

                    is ValidateShopInfoUseCase.Result.Failure -> {
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.ShowSnackbar((result.uiText)))
                        }
                    }
                }
            }
        }
    }


}