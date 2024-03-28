package com.example.todocompose.feature_todo.presentation.tasks.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todocompose.feature_todo.presentation.tasks.TaskViewModel
import com.example.todocompose.feature_todo.presentation.tasks.TasksEvent
import com.example.todocompose.feature_todo.presentation.util.Screen
import kotlin.math.round

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
                title = { Text(text = "Today's Todos", fontWeight = FontWeight.Bold) },

                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF96B1B9),
                    titleContentColor = Color(0xFF1E2021)
                ),


            )
        },
        content = { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding),
                color = Color(0xFF404C50)

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
                containerColor = Color(0xFF96B1B9),
                onClick = {
                navController.navigate(Screen.AddEditTaskScreen.route)
            }) {

                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }



    )
}