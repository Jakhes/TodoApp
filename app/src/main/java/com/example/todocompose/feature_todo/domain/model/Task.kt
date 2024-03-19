package com.example.todocompose.feature_todo.domain.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val description: String,
    var isCompleted: Boolean
)

class InvalidTaskException(message: String): Exception(message)
