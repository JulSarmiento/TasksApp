package com.julhdev.pendientes.utils

/**
 * A sealed class representing the different states of a UI.
 * @param T The type of data associated with the state.
 */
sealed class UIState<out T> {
  object Loading : UIState<Nothing>()
  data class Success<T>(var data: T) : UIState<T>()
  data class Error(var message: String) : UIState<Nothing>()
}