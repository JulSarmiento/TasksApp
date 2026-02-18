package com.julhdev.pendientes.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.julhdev.pendientes.ui.theme.TextPrimary


/**
 * Composable function representing a top bar in the UI.
 * @param title The title to be displayed in the top bar.
 * @receiver The Modifier to be applied to the top bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
  title: String,
) {
  TopAppBar(
    title = {
      Text(
        text = title,
      )
    },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = Color.Transparent,
      titleContentColor = TextPrimary,
    )
  )
}