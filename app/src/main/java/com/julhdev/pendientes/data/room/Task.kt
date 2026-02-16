package com.julhdev.pendientes.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Represents a note entity in the database.
 * @param id The unique identifier for the note.
 * @param content The content of the note.
 * @param timestamp The timestamp indicating when the note was created or last modified.
 * @param isCompleted A flag indicating whether the note is completed or not.
 * @param hasPriority A flag indicating whether the note has a priority or not.
 * @constructor Creates a new instance of the [Task] entity.
 */
@Entity(tableName = "tasks")
data class Task(

  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,

  @ColumnInfo(name = "content")
  val content: String,

  @ColumnInfo(name = "timestamp")
  val timestamp: Long = System.currentTimeMillis(),

  @ColumnInfo(name = "isCompleted")
  val isCompleted: Boolean = false,

  @ColumnInfo(name = "hasPriority")
  val hasPriority: Boolean = false
)
