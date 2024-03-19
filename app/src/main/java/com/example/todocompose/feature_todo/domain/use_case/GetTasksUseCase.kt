package com.example.todocompose.feature_todo.domain.use_case

import com.example.todocompose.feature_todo.domain.model.Task
import com.example.todocompose.feature_todo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(
    private val repository: TaskRepository
    ) {

    operator fun invoke(): Flow<List<Task>>
    {
        return repository.getTasks()
    }
}