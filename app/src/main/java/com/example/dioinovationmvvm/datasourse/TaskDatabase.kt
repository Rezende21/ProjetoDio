package com.example.dioinovationmvvm.datasourse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dioinovationmvvm.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun getInstance() : TaskDaoSource
}
