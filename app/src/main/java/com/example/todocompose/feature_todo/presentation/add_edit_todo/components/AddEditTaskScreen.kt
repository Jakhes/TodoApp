package com.example.todocompose.feature_todo.presentation.add_edit_todo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todocompose.feature_todo.presentation.add_edit_todo.AddEditTaskEvent
import com.example.todocompose.feature_todo.presentation.add_edit_todo.AddEditTaskViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreen(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    val descriptionState = viewModel.description.value

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true)
    {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditTaskViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditTaskViewModel.UiEvent.SaveTask -> {
                    navController.navigateUp()
                }
            }
        }
    }
    
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                viewModel.onEvent(AddEditTaskEvent.SaveTask)
                }
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save Task")
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        PaddingValues ->
        Surface(color = Color.DarkGray) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TransparentHintTextField(
                    text = descriptionState.text,
                    hint = descriptionState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditTaskEvent.EnteredTask(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditTaskEvent.ChangeTaskFocus(it))
                    },
                    isHintVisible = descriptionState.isHintVisible,
                    textStyle = MaterialTheme.typography.displaySmall,
                )


            }
        }
    }
}