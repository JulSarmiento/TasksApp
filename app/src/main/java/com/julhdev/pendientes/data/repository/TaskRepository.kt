package com.julhdev.pendientes.data.repository

import com.julhdev.pendientes.data.room.Task
import com.julhdev.pendientes.data.room.TaskDao
import com.julhdev.pendientes.utils.UIState
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart


/**
 * Repository class for managing tasks in the database.
 * This class provides methods for performing database operations related to tasks.
 * @property taskDao The DAO for managing tasks in the database.
 */
class TaskRepository @Inject constructor(
  private val taskDao: TaskDao
) {

  /**
   * Retrieves a flow of all tasks from the database.
   * @return A flow emitting a list of all tasks in the database.
   */
  fun getTasks(): Flow<UIState<List<Task>>> {
    return taskDao.getTasks()
      .map<List<Task>, UIState<List<Task>>> {
        UIState.Success(it)
      }
      .onStart {
        emit(UIState.Loading)
      }
      .catch { e ->
        emit(UIState.Error(e.message.toString()))
      }
      .flowOn(Dispatchers.IO)
  }

  /**
   * Retrieves a flow of a specific task from the database.
   * @param id The ID of the task to retrieve.
   * @return A flow emitting the specified task from the database.
   */
  fun getTask(id: Int): Flow<UIState<Task?>> {
    return taskDao.getTask(id)
      .map<Task?, UIState<Task?>> {
        UIState.Success(it)
      }
      .onStart {
        emit(UIState.Loading)
      }
      .catch { e ->
        emit(UIState.Error(e.message.toString()))
      }
      .flowOn(Dispatchers.IO)
  }

  /**
   * Inserts a new task into the database.
   * @param task The task to insert.
   * @return The ID of the inserted task.
   */
  suspend fun insertTask(task: Task) {
    taskDao.insertTask(task)
  }

  /**
   * Updates an existing task in the database.
   * @param task The task to update.
   */
  suspend fun updateTask(task: Task) {
    taskDao.updateTask(task)
  }

  /**
   * Deletes a task from the database.
   * @param task The task to delete.
   */
  suspend fun deleteTask(task: Task) {
    taskDao.deleteTask(task)
  }

}