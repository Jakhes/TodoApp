package com.example.todocompose.feature_todo.presentation.add_edit_todo

data class TaskTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
