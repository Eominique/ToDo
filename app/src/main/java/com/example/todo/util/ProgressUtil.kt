package com.example.todo.util

import androidx.annotation.IntRange
import com.example.todo.data.Task

object ProgressUtil {

    fun getTasksDoneProgress(list: List<Task?>): Int =
        list.takeUnless { it.isEmpty() }?.let {
            ((it.filter { task -> task?.completed == true }
                .size / it.size.toDouble()) * 100).toInt()} ?: 0

    fun getPercentage(@IntRange(from = 0, to = 100) progress: Int) =
        progress.takeIf { it in 0..100 }?.let { "$it" } ?: "-%"

}