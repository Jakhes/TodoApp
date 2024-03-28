package com.example.todocompose.feature_todo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todocompose.feature_todo.presentation.add_edit_todo.components.AddEditTaskScreen
import com.example.todocompose.feature_todo.presentation.tasks.components.TodoTaskScreen
import com.example.todocompose.feature_todo.presentation.util.Screen
import com.example.todocompose.ui.theme.TodoComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoComposeTheme {

                MainSample()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSample() {

    todoFeature()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoComposeTheme {
        todoFeature()
    }
}

@Composable
fun todoFeature()
{
    Surface(color = Color(0xFF404C50)) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.TaskScreen.route)
        {
            composable(route = Screen.TaskScreen.route)
            {
                TodoTaskScreen(navController = navController)
            }
            composable(route = Screen.AddEditTaskScreen.route +
                "?taskId={taskId}",
            arguments = listOf(
                navArgument(
                    name = "taskId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
                )
            )
            {
                AddEditTaskScreen(
                    navController = navController
                )
            }
        }

    }
}

