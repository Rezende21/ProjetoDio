package com.example.dioinovationmvvm.application

import android.app.Application
import com.example.dioinovationmvvm.datasourse.TaskDatabase
import com.example.dioinovationmvvm.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.IllegalStateException


class TaskApplication : Application() {

    companion object {
        private var instance: TaskApplication? = null

        fun getInstance() =
            instance ?: throw IllegalStateException("Application not configured")
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


//    val database by lazy { TaskDatabase.getDatabase(this) }
//    val repository by lazy { TaskRepository(database.taskDaoSource()) }
}