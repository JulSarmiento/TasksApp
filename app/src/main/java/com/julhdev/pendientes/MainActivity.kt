package com.julhdev.pendientes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.julhdev.pendientes.ui.theme.PendientesTheme
import com.julhdev.pendientes.viewmodels.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.julhdev.pendientes.views.HomeView

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val viewModel: TaskViewModel by viewModels()
    enableEdgeToEdge()
    setContent {
      PendientesTheme {
        HomeView(
          viewModel,
        )
      }
    }
  }
}

