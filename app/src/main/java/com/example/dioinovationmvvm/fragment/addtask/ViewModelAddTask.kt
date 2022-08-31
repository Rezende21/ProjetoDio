package com.example.dioinovationmvvm.fragment.addtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dioinovationmvvm.model.Task
import com.example.dioinovationmvvm.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelAddTask(private val repository: Repository) : ViewModel() {

    fun insertTask(task: Task) {
        viewModelScope.launch { Dispatchers.IO
            repository.insertSingleTask(task)
        }
    }
}