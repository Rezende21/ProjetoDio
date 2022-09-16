package com.example.dioinovationmvvm.repository

import androidx.lifecycle.LiveData
import com.example.dioinovationmvvm.data.local.Task

interface Repository {

    suspend fun showAllTasks() : LiveData<List<Task>>
    suspend fun insertSingleTask(task : Task)
    suspend fun deleteSingleTask(task : Task)
    suspend fun updateSingleTask(task : Task)
}