package com.example.dioinovationmvvm.repository

import androidx.lifecycle.LiveData
import com.example.dioinovationmvvm.datasourse.TaskDaoSource
import com.example.dioinovationmvvm.model.Task

class TaskRepository (private val database: TaskDaoSource) : Repository {

    override suspend fun listAllTasks(): LiveData<List<Task>> {
        return database.getAllTask()
    }

    override suspend fun editSingleTask(task: Task) {
        database.updateTask(task)
    }

    override suspend fun deleteSingleTask(task: Task) {
        database.deleteTask(task)
    }

    override suspend fun insertSingleTask(task: Task) {
        database.insertTask(task)
    }

}