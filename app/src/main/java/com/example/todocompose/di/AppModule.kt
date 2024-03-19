package com.example.todocompose.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todocompose.feature_todo.data.data_source.TaskDatabase
import com.example.todocompose.feature_todo.data.repository.TaskRepositoryImpl
import com.example.todocompose.feature_todo.domain.repository.TaskRepository
import com.example.todocompose.feature_todo.domain.use_case.AddTaskUseCase
import com.example.todocompose.feature_todo.domain.use_case.DeleteTaskUseCase
import com.example.todocompose.feature_todo.domain.use_case.GetTaskUseCase
import com.example.todocompose.feature_todo.domain.use_case.GetTasksUseCase
import com.example.todocompose.feature_todo.domain.use_case.TaskUseCases
import com.example.todocompose.feature_todo.domain.use_case.ToogleTaskCompleteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            "tasks_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: TaskDatabase): TaskRepository {

        return TaskRepositoryImpl(db.taskDao)
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(repository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            getTasks = GetTasksUseCase(repository),
            deleteTask = DeleteTaskUseCase(repository),
            addTask = AddTaskUseCase(repository),
            getTask = GetTaskUseCase(repository),
            toggleTaskUseCase = ToogleTaskCompleteUseCase(repository)
        )
    }
}