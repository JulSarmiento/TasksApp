package com.julhdev.pendientes.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julhdev.pendientes.data.repository.TaskRepository
import com.julhdev.pendientes.data.room.Task
import com.julhdev.pendientes.data.useCases.GetTasksUseCase
import com.julhdev.pendientes.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for managing tasks.
 * This class provides methods for submitting, updating, and deleting tasks.
 */
@HiltViewModel
class TaskViewModel @Inject constructor(
  private val getTasksUseCase: GetTasksUseCase,
  private val repository: TaskRepository
) : ViewModel() {

  var contentInput by mutableStateOf("")
    private set

  val tasksState: StateFlow<UIState<List<Task>>> =
    repository.getTasks()
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UIState.Loading
      )



  /**
   * Updates the content of the input field.
   * @param value The new content value.
   */
  fun onContentChange(value: String) {
    contentInput = value
  }

  /**
   * Submits a new task to the repository.
   */
  fun insertTask() {
    if (contentInput.trim().isBlank()) return

    viewModelScope.launch(Dispatchers.IO) {
      try {
        repository.insertTask(Task(
          content = contentInput,
          timestamp = System.currentTimeMillis(),
          isCompleted = false,
          hasPriority = false
        ))
        contentInput = ""

      } catch (e: Exception) {
        UIState.Error(e.message.toString())
      }
    }
  }

  /**
   * Updates an existing task in the repository.
   * @param task The task to update.
   */
  fun updateTask(task: Task) {
    viewModelScope.launch(Dispatchers.IO) {
      try {
        repository.updateTask(task)
      } catch (e: Exception) {
        UIState.Error(e.message.toString())
      }
    }
  }

  /**
   * Deletes a task from the repository.
   * @param task The task to delete.
   */
  fun deleteTask(task: Task) {
    viewModelScope.launch(Dispatchers.IO) {
      try {
        repository.deleteTask(task)
      } catch (e: Exception) {
        UIState.Error(e.message.toString())
      }
    }
  }
}
