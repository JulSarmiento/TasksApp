package com.julhdev.pendientes.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.julhdev.pendientes.ui.theme.InputBackground
import com.julhdev.pendientes.ui.theme.NeonCyan

/**
 * A composable function that displays an input text field with a label and a trailing icon.
 * @param value The current value of the input text.
 * @param onValueChange The callback that is invoked when the input text changes.
 * @param action The callback that is invoked when the trailing icon is clicked.
 */
@Composable
fun InputText(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    showInputDialog: MutableState<Boolean>,
    action: () -> Unit = {},
  ) {

  val setError = remember { mutableStateOf(false) }
  val focusRequester = remember { FocusRequester() }

  LaunchedEffect(Unit) {
    focusRequester.requestFocus()
  }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 16.dp)
  ) {

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .shadow(
          elevation = 20.dp,
          shape = RoundedCornerShape(50),
          ambientColor = NeonCyan,
          spotColor = NeonCyan
        )
        .background(
          color = NeonCyan.copy(alpha = 0.2f),
          shape = RoundedCornerShape(50)
        )
        .padding(6.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {

      BasicTextField(
        value = value,
        onValueChange = {
          onValueChange(it)
          if (setError.value) setError.value = false
        },
        singleLine = true,
        modifier = Modifier
          .weight(1f) // 👈 importante
          .background(
            color = InputBackground,
            shape = RoundedCornerShape(50)
          )
          .padding(horizontal = 20.dp, vertical = 12.dp)
          .focusRequester(focusRequester)
          .then(modifier),
        decorationBox = { innerTextField ->
          Box(
            contentAlignment = Alignment.CenterStart
          ) {
            if (value.isEmpty()) {
              Text(
                text = placeholder,
                color = Color.Gray
              )
            }
            innerTextField()
          }
        },
        keyboardOptions = KeyboardOptions(
          imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
          onDone = {
            if (!setError.value) {
              action()
              onValueChange("")
              showInputDialog.value = false
            }
          }
        ),
      )

      Spacer(modifier = Modifier.width(8.dp))

      AddIconBtn(
        action = {
          setError.value =
            value.isBlank() || value.length < 3

          if (!setError.value) {
            action()
            onValueChange("")
            showInputDialog.value = false
          }
        },
        modifier = Modifier
          .size(48.dp)
          .background(
            color = NeonCyan,
            shape = CircleShape
          )
      )
    }

    if (setError.value) {
      Text(
        text = "El texto debe tener al menos 3 caracteres",
        modifier = Modifier.padding(16.dp),
        color = MaterialTheme.colorScheme.error
      )
    }
  }
}