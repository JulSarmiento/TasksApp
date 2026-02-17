package com.julhdev.pendientes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.julhdev.pendientes.components.AddIconBtn
import com.julhdev.pendientes.components.TopBar
import com.julhdev.pendientes.ui.theme.PendientesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      PendientesTheme {
        Scaffold(
          modifier = Modifier.fillMaxSize(),
          topBar = {
            TopBar(
              title = "Pendientes"
            )
          },
          floatingActionButton = {
            AddIconBtn(
              modifier = Modifier.padding(8.dp),
              action = {
                /* TODO */
              }
            )
          }
        ) { innerPadding ->
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
              .fillMaxSize()
              .padding(innerPadding)
          ) {

          }
        }
      }
    }
  }
}


@Composable
fun NoHomeContent() {
  Column(
    modifier = Modifier
      .fillMaxSize()
    ,
    verticalArrangement = Arrangement.Center,
  ) {
    Text(
      text = "No Home Content",
      modifier = Modifier.padding(16.dp)
    )
  }
}
