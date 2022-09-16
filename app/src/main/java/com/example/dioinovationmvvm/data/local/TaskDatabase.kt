package com.example.dioinovationmvvm.data.local


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun getInstance() : TaskDaoSource
}