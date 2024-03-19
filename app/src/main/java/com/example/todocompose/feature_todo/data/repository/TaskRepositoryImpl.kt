package com.example.todocompose.feature_todo.data.repository
import com.example.todocompose.feature_todo.data.data_source.TaskDao
import com.example.todocompose.feature_todo.domain.model.Task
import com.example.todocompose.feature_todo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val taskDao: TaskDao
    ) : TaskRepository {

    override fun getTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    override fun getTaskById(id: Int): Flow<Task> {
        return taskDao.getTaskById(id)
    }

    override suspend fun insertTask(task: Task) {
        taskDao.insert(task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.delete(task)
    }
}