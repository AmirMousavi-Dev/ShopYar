package ir.codroid.profile_presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.codroid.core.domain.preferences.Preferences
import ir.codroid.core.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var state by mutableStateOf(ProfileContract.State())
        private set

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val shopInfo = preferences.loadShopInfo() ?: return@launch
            state = state.copy(
                shopInfo = shopInfo
            )
        }
    }

}