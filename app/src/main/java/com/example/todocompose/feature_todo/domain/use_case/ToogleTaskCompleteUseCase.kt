package com.example.todocompose.feature_todo.domain.use_case

import com.example.todocompose.feature_todo.domain.model.Task
import com.example.todocompose.feature_todo.domain.repository.TaskRepository

class ToogleTaskCompleteUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        task.isCompleted = !task.isCompleted
        repository.insertTask(task)
    }
}