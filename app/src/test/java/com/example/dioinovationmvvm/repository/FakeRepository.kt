package com.example.dioinovationmvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dioinovationmvvm.data.local.Task

class FakeRepository : Repository {

    private val tasks = mutableListOf<Task>()
    private val showAllTask = MutableLiveData<List<Task>>()

    private fun refreshLiveData() {
        showAllTask.postValue(tasks)
    }

    override suspend fun showAllTasks(): LiveData<List<Task>> {
        return showAllTask
    }

    override suspend fun insertSingleTask(task: Task) {
        tasks.add(task)
        refreshLiveData()
    }

    override suspend fun deleteSingleTask(task: Task) {
        tasks.remove(task)
        refreshLiveData()
    }

    override suspend fun updateSingleTask(task: Task) {
        val taskId = task.id
        if (taskId != null) {
            tasks.removeAt(taskId)
        }
        tasks.add(task)
        refreshLiveData()
    }
}