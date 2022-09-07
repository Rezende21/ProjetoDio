package com.example.dioinovationmvvm.application

import android.app.Application
import com.example.dioinovationmvvm.datasourse.TaskDatabase
import com.example.dioinovationmvvm.repository.TaskRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.IllegalStateException


@HiltAndroidApp
class TaskApplication : Application()