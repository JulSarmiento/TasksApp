package com.julhdev.pendientes.data.repositoryImpl

import com.julhdev.pendientes.data.repository.SolidRepository
import com.julhdev.pendientes.data.room.Task
import com.julhdev.pendientes.data.room.TaskDao
import kotlinx.coroutines.flow.Flow

class SolidRepositoryImpl(
    private val dao: TaskDao,
): SolidRepository {

    override suspend fun getTasks(): Flow<List<Task>> {
        return dao.getTasks()
    }

    override suspend fun getTask(id: Int): Task? {
        return dao.getTask(id)
    }

    override suspend fun insertTask(task: Task) {
        dao.insertTask(task)
    }

    override suspend fun updateTask(task: Task) {
        dao.updateTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }
}

