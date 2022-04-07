package com.example.dioinovationmvvm.repository

import androidx.lifecycle.LiveData
import com.example.dioinovationmvvm.datasourse.TaskDaoSource
import com.example.dioinovationmvvm.datasourse.TaskDatabase
import com.example.dioinovationmvvm.model.Task

class TaskRepository(private val database: TaskDatabase) {

    val allTasks : LiveData<List<Task>> = database.taskDaoSource().getAllTask()

    suspend fun insertTask(task: Task) {
        database.taskDaoSource().insertTask(task)
    }

    suspend fun deleteTask(task: Task) {
        database.taskDaoSource().deleteTask(task)
    }

    suspend fun updateTask(task: Task) {
        database.taskDaoSource().updateTask(task)
    }

}