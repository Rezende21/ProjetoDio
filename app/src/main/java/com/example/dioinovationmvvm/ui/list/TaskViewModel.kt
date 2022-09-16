package com.example.dioinovationmvvm.ui.list

import androidx.lifecycle.*
import com.example.dioinovationmvvm.data.local.Task
import com.example.dioinovationmvvm.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var tasks : LiveData<List<Task>> = MutableLiveData()


    fun showAllTasks() : LiveData<List<Task>> {
        viewModelScope.launch {
            tasks = repository.showAllTasks()
        }
        return tasks
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
           repository.deleteSingleTask(task)
        }
    }
}

