package com.julhdev.pendientes.di

import android.content.Context
import androidx.room.Room
import com.julhdev.pendientes.data.room.TaskDao
import com.julhdev.pendientes.data.room.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module that provides dependencies for the application.
 * This module is installed in the [SingletonComponent] and provides singleton instances of
 * [TaskDao] and [TaskDatabase].
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Singleton
  @Provides
  fun providesTaskDao(taskDatabase: TaskDatabase): TaskDao {
    return taskDatabase.taskDao()
  }

  @Singleton
  @Provides
  fun providesTaskDatabase(@ApplicationContext context: Context): TaskDatabase {
    return Room.databaseBuilder(
      context,
      TaskDatabase::class.java,
      "task_database"
    )
      .fallbackToDestructiveMigration(false)
      .build()
  }

}