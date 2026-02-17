package com.julhdev.pendientes.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

@Composable
fun AddIconBtn (
  action: () -> Unit = {},
  modifier: Modifier
) {
    IconButton(
      onClick = action,
      modifier = Modifier
        .clip(MaterialTheme.shapes.small)
        .then(modifier),
      colors = IconButtonDefaults.iconButtonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
      ),
    ) {
      Icon(
        imageVector = Icons.Filled.Add,
        contentDescription = "Add",
        tint = Color.White
      )
    }
}