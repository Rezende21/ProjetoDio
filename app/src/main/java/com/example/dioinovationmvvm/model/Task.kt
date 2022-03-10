package com.example.dioinovationmvvm.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "TO_DO_LIST")
data class Task (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title : String,
    val hour : String,
    val date : String
) : Parcelable
