package com.julhdev.pendientes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.julhdev.pendientes.ui.theme.NeonCyan

/**
 * Composable function representing an icon button with a shadow effect.
 * @param action The action to be performed when the button is clicked.
 * @param modifier The modifier to be applied to the button.
 */
@Composable
fun AddIconBtn (
  action: () -> Unit = {},
  modifier: Modifier
) {
  IconButton(
    onClick = action,
    modifier = modifier
      .size(52.dp)
      .shadow(
        elevation = 20.dp,
        shape = CircleShape,
        ambientColor = NeonCyan,
        spotColor = NeonCyan
      )
      .background(
        color = NeonCyan,
        shape = CircleShape
      )
  ) {
    Icon(
      imageVector = Icons.Filled.Add,
      contentDescription = "Agregar nueva tarea",
      tint = Color.Black
    )
  }
}

/**
 * Composable function representing an icon button with a shadow effect.
 * @param validator The validator to be applied to the button.
 * @param action The action to be performed when the button is clicked.
 * @param iconDefault The default icon to be displayed.
 * @param iconChange The icon to be displayed when the validator is true.
 * @param tintDefault The default tint color of the icon.
 * @param tintChange The tint color of the icon when the validator is true.
 * @param contentDescription The content description of the icon.
 */
@Composable
fun CardIconBtn(
  validator: Boolean,
  action: () -> Unit = {},
  iconDefault: ImageVector,
  iconChange: ImageVector?,
  tintDefault: Color,
  tintChange: Color,
  contentDescription: String,
) {
  IconButton(
    onClick = action,
    modifier = Modifier
      .padding(end = 10.dp)
  ) {
    (if(iconChange == null) {
      iconDefault
    } else {
      if(validator) iconChange else iconDefault
    }).let {
      Icon(
        imageVector = it,
        contentDescription = contentDescription,
        tint = if (validator) tintChange else tintDefault,
        modifier = Modifier
          .padding(10.dp)
      )
    }
  }
}