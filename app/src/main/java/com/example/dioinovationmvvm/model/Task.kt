package com.example.dioinovationmvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "TO_DO_LIST")
data class Task (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "hour")
    var hour : String,

    @ColumnInfo(name = "date")
    var date : String

) : Serializable