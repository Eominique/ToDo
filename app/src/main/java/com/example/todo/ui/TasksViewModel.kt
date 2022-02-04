package com.example.todo.ui

import androidx.lifecycle.ViewModel
import com.example.todo.data.TaskDao
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao
): ViewModel() {
}