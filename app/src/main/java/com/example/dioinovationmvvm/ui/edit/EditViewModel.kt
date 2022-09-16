package com.example.dioinovationmvvm.ui.edit

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
class EditViewModel @Inject constructor(
    private val repository: Repository) : ViewModel() {

    private val _statusTask = MutableLiveData<Resorce<Task>>()
    val statusTask : LiveData<Resorce<Task>> = _statusTask

     private fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateSingleTask(task)
        }
    }

    fun checkTaskValue(taskNome : String, taskHour : String, taskData : String) {
        _statusTask.postValue(Resorce.loading(null))
        if (taskNome.isBlank()) {
            return _statusTask.postValue(Resorce.error("Não pode deixar esse campo vazio", null))
        }
        val task = Task(null, taskNome, taskHour, taskData)
        updateTask(task)
        _statusTask.postValue(Resorce.success(task))
    }
}