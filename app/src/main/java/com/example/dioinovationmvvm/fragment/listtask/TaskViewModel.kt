package com.example.dioinovationmvvm.fragment.listtask

import androidx.lifecycle.*
import com.example.dioinovationmvvm.model.Task
import com.example.dioinovationmvvm.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: Repository) : ViewModel() {

    private lateinit var tasks : LiveData<List<Task>>

    fun listTask() : LiveData<List<Task>>  {
        viewModelScope.launch {
            tasks = MutableLiveData()
            tasks = repository.listAllTasks()
        }
        return tasks
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch { Dispatchers.IO
           repository.deleteSingleTask(task)
        }
    }
}

