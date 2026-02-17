package com.julhdev.pendientes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.julhdev.pendientes.data.repository.TaskRepository
import com.julhdev.pendientes.data.room.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for managing tasks.
 * This class provides methods for submitting, updating, and deleting tasks.
 */
class TaskViewModel @Inject constructor(
  private val repository: TaskRepository
): ViewModel() {

  private val _tasks = MutableStateFlow<List<Task>>(emptyList())
  val tasks = _tasks.asStateFlow()

  init {
   viewModelScope.launch(Dispatchers.IO) {
     repository.getTasks().collect { tasks ->
       _tasks.value = tasks
     }
   }
  }

  /**
   * Submits a new task to the repository.
   * @param task The task to submit.
   */
  fun submitTask(task: Task) {
    viewModelScope.launch(Dispatchers.IO) {
      repository.insertTask(task)
    }
  }

  /**
   * Updates an existing task in the repository.
   * @param task The task to update.
   */
  fun updateTask(task: Task) {
    viewModelScope.launch(Dispatchers.IO) {
      repository.updateTask(task)
    }
  }

  /**
   * Deletes a task from the repository.
   * @param task The task to delete.
   */
  fun deleteTask(task: Task) {
    viewModelScope.launch(Dispatchers.IO) {
      repository.deleteTask(task)
    }
  }

}