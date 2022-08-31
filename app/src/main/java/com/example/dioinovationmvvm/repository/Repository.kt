package com.example.dioinovationmvvm.repository

import androidx.lifecycle.LiveData
import com.example.dioinovationmvvm.model.Task

interface Repository {

    suspend fun listAllTasks() : LiveData<List<Task>>
    suspend fun editSingleTask(task: Task)
    suspend fun deleteSingleTask(task: Task)
    suspend fun insertSingleTask(task: Task)

}