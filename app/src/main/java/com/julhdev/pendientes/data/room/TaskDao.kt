package com.julhdev.pendientes.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


/**
 * Data Access Object (DAO) interface for managing tasks in the database.
 * This interface defines methods for performing database operations related to tasks.
 */
@Dao
interface TaskDao {

  @Query("SELECT * FROM tasks  ORDER BY isCompleted DESC , hasPriority DESC, timestamp DESC")
  fun getTasks(): Flow<List<Task>>

  @Query("SELECT * FROM tasks WHERE id = :id")
  fun getTask(id: Int): Flow<Task?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertTask(task: Task)

  @Update
  suspend fun updateTask(task: Task)

  @Delete
  suspend fun deleteTask(task: Task)

}