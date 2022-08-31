package com.example.dioinovationmvvm.di

import androidx.room.Room
import com.example.dioinovationmvvm.datasourse.TaskDaoSource
import com.example.dioinovationmvvm.datasourse.TaskDatabase
import com.example.dioinovationmvvm.fragment.addtask.ViewModelAddTask
import com.example.dioinovationmvvm.fragment.edittask.ViewModelEditTask
import com.example.dioinovationmvvm.fragment.listtask.TaskViewModel
import com.example.dioinovationmvvm.repository.Repository
import com.example.dioinovationmvvm.repository.TaskRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            TaskDatabase::class.java,
            "TO_DO_LIST_TASK.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single<TaskDaoSource> {
        val database = get<TaskDatabase>()
        database.getInstance()
    }

    single<Repository> {
        TaskRepository(
            database =  get()
        )
    }

    viewModel {
        TaskViewModel(
            repository =  get()
        )
    }
    viewModel {
        ViewModelEditTask(
            repository = get()
        )
    }
    viewModel {
        ViewModelAddTask(
            repository = get()
        )
    }



}