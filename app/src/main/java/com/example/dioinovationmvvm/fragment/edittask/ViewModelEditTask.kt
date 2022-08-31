package com.example.dioinovationmvvm.fragment.edittask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dioinovationmvvm.model.Task
import com.example.dioinovationmvvm.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelEditTask(private val repository: Repository) : ViewModel() {

    fun updateTask(task: Task) {
        viewModelScope.launch { Dispatchers.IO
            repository.editSingleTask(task)
        }
    }
}