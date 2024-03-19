package com.example.todocompose.feature_todo.presentation.tasks

import com.example.todocompose.feature_todo.domain.model.Task

data class TasksState(
    val tasks: List<Task> = emptyList()
)
