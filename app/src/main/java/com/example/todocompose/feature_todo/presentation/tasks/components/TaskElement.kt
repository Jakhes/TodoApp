package com.example.todocompose.feature_todo.presentation.tasks.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.todocompose.feature_todo.domain.model.Task



@Composable
fun TaskElement(
    task: Task,
    modifier: Modifier,
    onDeleteClick: () -> Unit,
    onCheckedClick: (task: Task) -> Unit
) {
    val checked = rememberSaveable {
        mutableStateOf(task.isCompleted)
    }
    Box(
        modifier = modifier
    ) {
        Row() {
            Checkbox(checked = checked.value, onCheckedChange = {
                checked.value = !checked.value

                onCheckedClick(task)},
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF7D767A),
                uncheckedColor = Color.White
            ))
            Text(
                text = task.description,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f),
                color = Color.White
            )

            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Task",
                    tint = Color(0xFF918765)
                )
            }
        }
    }
}