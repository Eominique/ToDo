package com.example.todo.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat
import java.time.LocalDate
import java.util.*

@Entity(tableName = "task_table")
@Parcelize
data class Task(
    val title: String = "",
    val important: Boolean = false,
    val completed: Boolean = false,
  //  val created: Long = System.currentTimeMillis(),
    val created: Date = Calendar.getInstance().time,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable{

    val isActive
    get() = !completed

    val isEmpty
    get() = title.isEmpty()
}