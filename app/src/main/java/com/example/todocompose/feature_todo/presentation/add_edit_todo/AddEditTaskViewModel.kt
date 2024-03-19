package com.example.todocompose.feature_todo.presentation.add_edit_todo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.todocompose.feature_todo.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

import androidx.lifecycle.viewModelScope
import com.example.todocompose.feature_todo.domain.model.InvalidTaskException
import com.example.todocompose.feature_todo.domain.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _description = mutableStateOf(
        TaskTextFieldState(
        hint = "Enter task description"
    )
    )
    val description: State<TaskTextFieldState> = _description

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTaskId: Int? = null
    private var currentTaskCompleteness: Boolean = false

    init {
        savedStateHandle.get<Int>("taskId")?.let { taskId ->
            if (taskId != -1) {
                taskUseCases.getTask(taskId).onEach { task ->
                    currentTaskId = task.id
                    currentTaskCompleteness = task.isCompleted
                    _description.value = description.value.copy(
                        text = task.description,
                        isHintVisible = false
                    )
                }.launchIn(viewModelScope)
            }
        }
    }

    fun onEvent(event: AddEditTaskEvent) {
        when(event) {
            is AddEditTaskEvent.EnteredTask -> {
                _description.value = description.value.copy(
                    text = event.value
                )
            }
            is AddEditTaskEvent.ChangeTaskFocus -> {
                _description.value = description.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            description.value.text.isBlank()
                )
            }
            is AddEditTaskEvent.SaveTask -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        taskUseCases.addTask(
                            Task(
                                description = description.value.text,
                                id = currentTaskId,
                                isCompleted = currentTaskCompleteness
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTask)
                    } catch (e: InvalidTaskException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Could not save Task"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveTask: UiEvent()
    }
}