package com.example.todocompose.feature_todo.data.data_source
import androidx.room.*
import com.example.todocompose.feature_todo.domain.model.Task


@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao
}
