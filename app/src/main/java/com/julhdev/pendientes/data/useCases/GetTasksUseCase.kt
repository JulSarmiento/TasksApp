package com.julhdev.pendientes.data.useCases

import com.julhdev.pendientes.data.repositoryImpl.SolidRepositoryImpl
import com.julhdev.pendientes.data.room.Task
import com.julhdev.pendientes.utils.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class GetTasksUseCase(
    private val repository: SolidRepositoryImpl
) {
    suspend operator fun invoke(id: Int): Flow<UIState<List<Task>>> {
        return repository.getTasks()
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

}