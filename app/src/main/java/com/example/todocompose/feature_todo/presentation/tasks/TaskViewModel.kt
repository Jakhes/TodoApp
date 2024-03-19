package com.example.todocompose.feature_todo.presentation.tasks

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todocompose.feature_todo.data.data_source.TaskDatabase
import com.example.todocompose.feature_todo.data.repository.TaskRepositoryImpl
import com.example.todocompose.feature_todo.domain.model.Task
import com.example.todocompose.feature_todo.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel(){

    private val _state = mutableStateOf<TasksState>(TasksState())
    val state: State<TasksState> = _state

    
    private var getTasksJob: Job? = null

    init {
        getTasks()
    }

    fun onEvent(event: TasksEvent) {
        when(event) {
            is TasksEvent.DeleteTask -> {
                viewModelScope.launch(Dispatchers.IO) {
                    taskUseCases.deleteTask(event.task)
                }
            }
            is TasksEvent.ToggleTaskComplete -> {
                viewModelScope.launch(Dispatchers.IO) {
                    taskUseCases.toggleTaskUseCase(event.task)
                }
            }
        }
    }

    private fun getTasks() {
        getTasksJob?.cancel()
        getTasksJob = taskUseCases.getTasks()
            .onEach { tasks ->
                _state.value = state.value.copy(
                    tasks = tasks
                )
            }.launchIn(viewModelScope)
    }
}


