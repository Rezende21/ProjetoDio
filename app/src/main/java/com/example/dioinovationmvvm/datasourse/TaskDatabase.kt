package com.example.dioinovationmvvm.datasourse


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dioinovationmvvm.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDaoSource() : TaskDaoSource

    companion object {
    @Volatile
    private var INSTANCE : TaskDatabase? = null

    fun getDatabase(context: Context) : TaskDatabase {
        val tempInstance = INSTANCE
        if (tempInstance != null) {
            return tempInstance
        }
        synchronized(this) {
                 val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "TO_DO_LIST_TASK.db"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                return instance
            }
        }
    }
}