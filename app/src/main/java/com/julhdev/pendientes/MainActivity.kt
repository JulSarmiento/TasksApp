package com.julhdev.pendientes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.julhdev.pendientes.components.AddIconBtn
import com.julhdev.pendientes.components.TopBar
import com.julhdev.pendientes.ui.theme.PendientesTheme
import com.julhdev.pendientes.viewmodels.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.julhdev.pendientes.components.InputText
import com.julhdev.pendientes.data.room.Task
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val viewModel: TaskViewModel by viewModels()
    enableEdgeToEdge()
    setContent {
      PendientesTheme {

        val showInputDialog = rememberSaveable { mutableStateOf(false) }
        val inputText = rememberSaveable { mutableStateOf("") }
        val tasks by viewModel.tasks.collectAsState()

        Log.d("AYUUUDAAA", tasks.toString())

        Scaffold(
          modifier = Modifier.fillMaxSize(),
          topBar = {
            TopBar(
              title = "Mis tareas"
            )
          },
          floatingActionButton = {
            AddIconBtn(
              modifier = Modifier.padding(8.dp),
              action = {
                showInputDialog.value = true
              }
            )
          }
        ) { innerPadding ->
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
              .fillMaxSize()
              .padding(innerPadding)
              .padding(horizontal = 10.dp)
          ) {


            if(showInputDialog.value) {
              InputText(
                placeholder = "New Task",
                value = inputText.value,
                onValueChange = { inputText.value = it },
                showInputDialog = showInputDialog,
                action = {
                  viewModel.insertTask(
                    Task(
                      content = inputText.value,
                      timestamp = System.currentTimeMillis()
                    )
                  )
                }
              )
            }

            if(tasks.isEmpty()) {
              NoHomeContent()
            } else {
              HomeContent(
                viewModel,
                tasks
              )
            }
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
  ) {
    Text(
      text = "No Home Content",
      modifier = Modifier.padding(16.dp)
    )
  }
}


@Composable
fun HomeContent(
  viewModel: TaskViewModel,
  tasks: List<Task> = emptyList()
) {
  LazyColumn(
    modifier = Modifier
      .fillMaxWidth()
    ,
    verticalArrangement = Arrangement.Center,
  ) {
    items(tasks) {

      val delete = SwipeAction(
        icon = rememberVectorPainter(
          image = Icons.Default.Delete
        ),
        background = Color.Red,
        onSwipe = {
          viewModel.deleteTask(it)
        }
      )
      SwipeableActionsBox(
        endActions = listOf(delete),
        swipeThreshold = 50.dp
      ) {
        Text(
          text = it.content,
          modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        )
      }
    }
  }
}
