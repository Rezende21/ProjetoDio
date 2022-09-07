package com.example.dioinovationmvvm.di

import android.content.Context
import androidx.room.Room
import com.example.dioinovationmvvm.datasourse.TaskDaoSource
import com.example.dioinovationmvvm.datasourse.TaskDatabase
import com.example.dioinovationmvvm.repository.Repository
import com.example.dioinovationmvvm.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideInstanceFromDB(@ApplicationContext context : Context) : TaskDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            TaskDatabase::class.java,
            "TO_DO_LIST_TASK.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideTaskDao(database: TaskDatabase) : TaskDaoSource = database.getInstance()

    @Provides
    @Singleton
    fun provideRepositoryTask(dao : TaskDaoSource) : Repository {
        return TaskRepository(dao)
    }
}