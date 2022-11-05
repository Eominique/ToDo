package com.example.todo.util

import com.example.todo.util.SortOrder

data class FilterPreferences(
    val sortOrder: SortOrder,
    val hideCompleted: Boolean
)
