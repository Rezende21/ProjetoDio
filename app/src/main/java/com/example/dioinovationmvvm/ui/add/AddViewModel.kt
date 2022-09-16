package com.example.dioinovationmvvm.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dioinovationmvvm.data.local.Task
import com.example.dioinovationmvvm.others.Resorce
import com.example.dioinovationmvvm.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repository: Repository) : ViewModel() {

    private val _taskStatus = MutableLiveData<Resorce<Task>>()
    val taskStatus : LiveData<Resorce<Task>> = _taskStatus

    private fun insertTask(task: Task) {
        viewModelScope.launch {
            repository.insertSingleTask(task)
        }
    }

    fun verifyTaskValue(taskName : String, taskHour : String, taskDate : String) {
        _taskStatus.postValue(Resorce.loading(null))
        if(taskName.isEmpty()) {
            return _taskStatus.postValue(Resorce.error("Coloque o titulo da task", null))
        }
        val task = Task(null, taskName, taskHour, taskDate)
        insertTask(task)
        _taskStatus.postValue(Resorce.success(task))
    }
}