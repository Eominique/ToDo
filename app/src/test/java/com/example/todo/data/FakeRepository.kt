package com.example.todo.data

import androidx.lifecycle.MutableLiveData
import com.example.todo.util.SortOrder
import kotlinx.coroutines.flow.Flow


class FakeRepository constructor() : TaskDao {

    var tasksServiceData: LinkedHashMap<String, Task> = LinkedHashMap()
    private var shouldReturnError = false
    private val observableTasks = MutableLiveData<List<Task>>()

    override fun getTasks(
        query: String,
        sortOrder: SortOrder,
        hideCompleted: Boolean
    ): Flow<List<Task>> {
        return super.getTasks(query, sortOrder, hideCompleted)
    }

    override fun getTasksSortedByName(
        searchQuery: String,
        hideCompleted: Boolean
    ): Flow<List<Task>> {
        TODO("Not yet implemented")
    }

    override fun getTasksSortedByDateCreated(
        searchQuery: String,
        hideCompleted: Boolean
    ): Flow<List<Task>> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(task: Task) {
      tasksServiceData[task.id.toString()] = task
    }

    override suspend fun update(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(task: Task) {
    tasksServiceData.remove(task.id.toString())
    }

    override suspend fun deleteCompletedTask() {
        tasksServiceData.filterValues {
            !it.completed
        }

    }

    override suspend fun getTaskById(taskId: String): Task? {
        TODO("Not yet implemented")
    }


}