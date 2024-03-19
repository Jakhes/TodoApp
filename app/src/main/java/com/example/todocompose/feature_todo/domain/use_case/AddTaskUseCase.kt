package com.example.todocompose.feature_todo.domain.use_case

import com.example.todocompose.feature_todo.domain.model.InvalidTaskException
import com.example.todocompose.feature_todo.domain.model.Task
import com.example.todocompose.feature_todo.domain.repository.TaskRepository

class AddTaskUseCase(
    private val repository: TaskRepository
) {

    @Throws(InvalidTaskException::class)
    suspend operator fun invoke(task: Task){
        if (task.description.isBlank()) {
            throw InvalidTaskException("The Task has no Description.")
        }
        repository.insertTask(task)
    }
}