package com.example.todo.ui.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.todo.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao: TaskDao
): ViewModel() {

    val tasks = taskDao.getTasks().asLiveData()
}