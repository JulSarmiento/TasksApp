package com.julhdev.pendientes.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


/**
 * Represents the Room database for managing tasks.
 * This class defines the database configuration and provides access to the task data access object (DAO).
 * @property taskDao The DAO for managing tasks in the database.
 */
@Database(
  entities = [Task::class],
  version = 1,
  exportSchema = false
)
abstract class TaskDatabase : RoomDatabase(){
  abstract fun taskDao(): TaskDao
}