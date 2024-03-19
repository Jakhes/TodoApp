package com.example.todocompose.feature_todo.presentation.tasks.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todocompose.feature_todo.presentation.tasks.TaskViewModel
import com.example.todocompose.feature_todo.presentation.tasks.TasksEvent
import com.example.todocompose.feature_todo.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()


    var show by remember {
        mutableStateOf(false)
    }

    // A surface container using the 'background' color from the theme



    Scaffold(
        modifier = Modifier.padding(16.dp),
        topBar = {
            TopAppBar(
                title = { Text(text = "Todo App") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.hsv(100.0f, 0.50f, 0.50f),
                    titleContentColor = Color.White
                )
            )
        },
        content = { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding),
                color = Color.DarkGray

            )   {
                LazyColumn(modifier = Modifier.fillMaxSize()){
                    items(items = state.tasks, key = { it.id.hashCode() }) { currentTask ->
                        TaskElement(
                            task = currentTask,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        Screen.AddEditTaskScreen.route +
                                                "?taskId=${currentTask.id}"
                                    )
                                },
                            onDeleteClick = { viewModel.onEvent(TasksEvent.DeleteTask(currentTask)) },
                            onCheckedClick = { viewModel.onEvent(TasksEvent.ToggleTaskComplete(currentTask))}
                        )
                        Divider(color = Color.LightGray)
                    }
                }

            }
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color.hsv(219f, 0.41f, 0.56f),
                onClick = {
                navController.navigate(Screen.AddEditTaskScreen.route)
            }) {

                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }



    )
}