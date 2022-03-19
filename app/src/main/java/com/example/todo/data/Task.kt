package com.example.todo.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat
import java.time.LocalDate

@Entity(tableName = "task_table")
@Parcelize
data class Task(
    val name: String,
    val important: Boolean = false,
    val completed: Boolean = false,
    @ColumnInfo(defaultValue = "0")
    val deadline: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable