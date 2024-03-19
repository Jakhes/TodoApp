package com.example.todocompose.feature_todo.presentation.add_edit_todo

import androidx.compose.ui.focus.FocusState

sealed class AddEditTaskEvent{
    data class EnteredTask(val value: String): AddEditTaskEvent()
    data class ChangeTaskFocus(val focusState: FocusState): AddEditTaskEvent()
    object SaveTask: AddEditTaskEvent()
}
