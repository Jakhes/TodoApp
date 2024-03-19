package com.example.todocompose.feature_todo.data.data_source
import androidx.room.*
import com.example.todocompose.feature_todo.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

//
//    @Update
//    suspend fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE Id = :id")
    fun getTaskById(id: Int): Flow<Task>
}