package ir.codroid.core.util

sealed class UiEvent {
    data object Success : UiEvent()

    data object Failure : UiEvent()

    data class Navigate(val route: String) : UiEvent()
    data object NavigateUp : UiEvent()
    data class ShowSnackbar(val message: UiText) : UiEvent()
}