package com.example.todocompose.feature_todo.presentation.tasks

import com.example.todocompose.feature_todo.domain.model.Task

sealed class TasksEvent {
    data class DeleteTask(val task: Task): TasksEvent()
    data class ToggleTaskComplete(val task: Task): TasksEvent()
}
