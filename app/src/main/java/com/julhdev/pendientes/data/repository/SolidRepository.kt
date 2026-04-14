package com.julhdev.pendientes.data.repository

import com.julhdev.pendientes.data.room.Task
import kotlinx.coroutines.flow.Flow

interface SolidRepository {

    suspend fun getTasks(): Flow<List<Task>>

    suspend fun getTask(id: Int): Task?

    suspend fun insertTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)
}
