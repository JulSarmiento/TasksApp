package com.julhdev.pendientes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppColorScheme = darkColorScheme(
  primary = NeonCyan,
  secondary = BackgroundEnd,
  background = Color.Black,
  surface = CardDark,

  onPrimary = Color.Black,
  onBackground = TextPrimary,
  onSurface = TextPrimary
)
@Composable
fun PendientesTheme(
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colorScheme = AppColorScheme,
    typography = Typography,
    content = content
  )
}