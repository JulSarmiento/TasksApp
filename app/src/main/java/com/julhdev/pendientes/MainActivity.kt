package com.julhdev.pendientes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import com.julhdev.pendientes.components.InputText
import com.julhdev.pendientes.components.TaskCard
import com.julhdev.pendientes.data.room.Task
import com.julhdev.pendientes.ui.theme.BackgroundEnd
import com.julhdev.pendientes.ui.theme.BackgroundStart
import com.julhdev.pendientes.ui.theme.NeonCyan
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
              modifier = Modifier
                .shadow(
                  25.dp,
                  shape = CircleShape,
                  ambientColor = NeonCyan,
                  spotColor = NeonCyan
                ),
              action = {
                showInputDialog.value = true
              }
            )
          }
        ) { innerPadding ->

          val gradient = Brush.verticalGradient(
            colors = listOf(
              BackgroundStart,
              BackgroundEnd
            )
          )

          Box(
            modifier = Modifier
              .background(gradient)
              .fillMaxSize()
          ) {
            Column(
              horizontalAlignment = Alignment.CenterHorizontally,
              modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 10.dp)
            ) {
              if (showInputDialog.value) {
                InputText(

                  placeholder = "Nueva Tarea",
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
                  },
                )

                Spacer(
                  modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                )
              }

              if (tasks.isEmpty()) {
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
}

/**
 * Composable function representing the content of the home screen when there are no tasks.
 * @receiver The Modifier to be applied to the content.
 */
@Composable
fun NoHomeContent() {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(32.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    Icon(
      imageVector = Icons.Default.CheckCircleOutline,
      contentDescription = null,
      tint = NeonCyan.copy(alpha = 0.4f),
      modifier = Modifier.size(100.dp)
    )

    Spacer(modifier = Modifier.height(24.dp))

    Text(
      text = "No tienes pendientes :) ",
      style = MaterialTheme.typography.titleLarge,
      color = Color.White
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = "Agrega una tarea para empezar y mantenerte productivo",
      style = MaterialTheme.typography.bodyMedium,
      color = Color.White.copy(alpha = 0.6f),
      textAlign = TextAlign.Center
    )
  }
}


/**
 * Composable function representing the content of the home screen.
 * @param viewModel The view model for managing tasks.
 * @param tasks The list of tasks to display.
 */
@Composable
fun HomeContent(
  viewModel: TaskViewModel,
  tasks: List<Task> = emptyList()
) {
  LazyColumn(
    verticalArrangement = Arrangement.spacedBy(10.dp),
    modifier = Modifier
      .fillMaxSize(),
  ) {
    items(tasks) {

      val delete = SwipeAction(
        icon = rememberVectorPainter(
          image = Icons.Default.Delete,
        ),
        background = NeonCyan.copy(alpha = 0.2f),
        onSwipe = {
          viewModel.deleteTask(it)
        }
      )
      SwipeableActionsBox(
        endActions = listOf(delete),
        swipeThreshold = 150.dp
      ) {
        TaskCard(
          isCompleted = it.isCompleted,
          onChangeCompleted = {
            viewModel.updateTask(it.copy(isCompleted = !it.isCompleted))
          },
          hasPriority = it.hasPriority,
          onChangePriority = {
            viewModel.updateTask(it.copy(hasPriority = !it.hasPriority))
          },
          content = it.content,
          timestamp = it.timestamp,
        )
        Spacer(modifier = Modifier.height(10.dp))
      }
    }
  }
}
