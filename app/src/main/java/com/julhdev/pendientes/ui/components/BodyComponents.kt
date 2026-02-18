package com.julhdev.pendientes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.julhdev.pendientes.ui.theme.BackgroundStart
import com.julhdev.pendientes.ui.theme.TaskCardColor
import com.julhdev.pendientes.utils.formatDate

/**
 * Composable function representing a task card in the UI.
 * @param isCompleted A boolean indicating whether the task is completed or not.
 * @param onChangeCompleted A callback to be invoked when the task's completion status is changed.
 * @param hasPriority A boolean indicating whether the task has a priority or not.
 * @param onChangePriority A callback to be invoked when the task's priority status is changed.
 * @param content The content of the task.
 * @param timestamp The timestamp indicating when the task was created or last modified.
 * @receiver The Modifier to be applied to the card.
 */
@Composable
fun TaskCard(
  isCompleted: Boolean,
  onChangeCompleted: () -> Unit,
  hasPriority: Boolean,
  onChangePriority: () -> Unit,
  content: String,
  timestamp: Long,
) {

  Card(
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(
      containerColor = TaskCardColor
    ),
    elevation = CardDefaults.cardElevation(
      defaultElevation = 10.dp
    ),
    modifier = Modifier
      .fillMaxWidth()
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)

    ) {
      CardIconBtn(
        validator = isCompleted,
        action = onChangeCompleted,
        iconDefault = Icons.Filled.Circle,
        iconChange = Icons.Filled.Check,
        tintDefault = Color.LightGray,
        tintChange = Color.Green,
        contentDescription = "Completar tarea"
      )

      Spacer(modifier = Modifier.width(12.dp))

      Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
          .fillMaxSize()
          .weight(1f)
      ) {
        Text(
          text = content,
          textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None,
          color = if (isCompleted) Color.LightGray else Color.White,
          textAlign = TextAlign.Start,
        )

        Text(
          text = formatDate(timestamp),
          fontSize = 12.sp,
          color = Color.LightGray,
        )
      }

      CardIconBtn(
        validator = hasPriority,
        action = onChangePriority,
        iconDefault = Icons.Filled.Flag,
        iconChange = null,
        tintDefault = Color.LightGray,
        tintChange = BackgroundStart,
        contentDescription = "Cambiar prioridad de la tarea"
      )
    }
  }
}