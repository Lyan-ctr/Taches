package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todolist.data.TaskRepository
import kotlinx.coroutines.flow.StateFlow
import com.example.todolist.model.Task

class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()

    // Le ViewModel observe les changements du Repository
    val tasks: StateFlow<List<Task>> = repository.tasks

    fun toggleTask(taskId: Int) {
        repository.toggleTask(taskId)
    }
}