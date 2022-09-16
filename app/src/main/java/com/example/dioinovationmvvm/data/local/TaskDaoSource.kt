package com.example.dioinovationmvvm.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDaoSource {

    @Query("SELECT * FROM TO_DO_LIST")
    fun getAllTask() : LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task : Task)

    @Delete
    suspend fun deleteTask(task : Task)

    @Update
    suspend fun updateTask(task: Task)

}