package com.example.todocompose.feature_todo.domain.use_case

data class TaskUseCases(
    val getTasks: GetTasksUseCase,
    val deleteTask: DeleteTaskUseCase,
    val addTask: AddTaskUseCase,
    val getTask: GetTaskUseCase,
    val toggleTaskUseCase: ToogleTaskCompleteUseCase
)
