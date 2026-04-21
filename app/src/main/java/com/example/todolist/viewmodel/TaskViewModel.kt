package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.repository.TaskRepository
import com.example.todolist.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    val tasks: StateFlow<List<Task>> = repository.tasks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // NOUVEAU : Fonction pour récupérer une tâche spécifique
    fun getTaskById(taskId: Int): Flow<Task?> {
        return repository.getTaskById(taskId)
    }

    fun addTask(name: String, dateDebut: LocalDate, dateFin: LocalDate) {
        viewModelScope.launch {
            repository.addTask(name, dateDebut, dateFin)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}
