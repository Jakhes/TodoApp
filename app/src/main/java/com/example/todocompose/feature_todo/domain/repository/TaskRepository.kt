package com.example.todocompose.feature_todo.domain.repository

import android.provider.ContactsContract.CommonDataKinds.Note
import com.example.todocompose.feature_todo.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks(): Flow<List<Task>>

    fun getTaskById(id: Int): Flow<Task>

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)
}