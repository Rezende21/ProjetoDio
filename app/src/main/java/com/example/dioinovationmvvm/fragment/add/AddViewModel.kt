package com.example.dioinovationmvvm.fragment.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dioinovationmvvm.model.Task
import com.example.dioinovationmvvm.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun insertTask(task: Task) {
        viewModelScope.launch { Dispatchers.IO
            repository.insertSingleTask(task)
        }
    }
}