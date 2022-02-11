package com.example.todo.ui.addtask

sealed class AddEditTaskEvent {
    data class ShowInvalidInputMessage(val msg: String) : AddEditTaskEvent()
    data class NavigateBackWithResult(val result: Int) : AddEditTaskEvent()
}