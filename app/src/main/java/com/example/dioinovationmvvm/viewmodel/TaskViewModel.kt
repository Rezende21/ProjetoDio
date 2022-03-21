package com.example.dioinovationmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.dioinovationmvvm.datasourse.TaskDatabase.Companion.getDatabase
import com.example.dioinovationmvvm.model.Task
import com.example.dioinovationmvvm.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TaskRepository(getDatabase(application))

    val tasks = repository.allTasks


    fun insertTask(task: Task) {
        viewModelScope.launch { Dispatchers.IO
            repository.insertTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch { Dispatchers.IO
           repository.deleteTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch { Dispatchers.IO
            repository.updateTask(task)
        }
    }


    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TaskViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct TaskViewModel")
        }

    }

}

