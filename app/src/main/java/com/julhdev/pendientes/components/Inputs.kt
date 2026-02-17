package com.julhdev.pendientes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A composable function that displays an input text field with a label and a trailing icon.
 * @param value The current value of the input text.
 * @param onValueChange The callback that is invoked when the input text changes.
 * @param action The callback that is invoked when the trailing icon is clicked.
 */
@Composable
fun InputText(
  placeholder: String,
  value: String,
  onValueChange: (String) -> Unit,
  showInputDialog: MutableState<Boolean>,
  action: () -> Unit = {}
) {

  val setError = remember { mutableStateOf(false) }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 16.dp)
  ) {
    OutlinedTextField(
      modifier = Modifier
        .fillMaxWidth(),
      value = value,
      onValueChange = onValueChange,
      label = { Text(placeholder) },
      isError = setError.value,
      trailingIcon = {
        AddIconBtn(
          action = {
            setError.value = value.isEmpty() || value.isBlank() || value.length < 3
            if (!setError.value) {
              action()
              onValueChange("")
              showInputDialog.value = false
            }
          },
          modifier = Modifier
            .padding(8.dp)
        )
      }
    )

    Text(
      text = when (setError.value) {
        true -> "Content must be at least 3 characters long"
        else -> ""
      },
      modifier = Modifier.padding(16.dp),
      color = MaterialTheme.colorScheme.error
    )
  }
}