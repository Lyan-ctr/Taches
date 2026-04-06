package com.example.todolist.data

import com.example.todolist.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskRepository {
    // On crée une liste initiale de pays
    private val _tasks = MutableStateFlow<List<Task>>(
        listOf(
            Task(id = 1, name = "🇫🇷 France", isDone = false),
            Task(id = 2, name = "🇧🇪 Belgique", isDone = false),
            Task(id = 3, name = "🇨🇦 Canada", isDone = false),
            Task(id = 4, name = "🇸🇳 Sénégal", isDone = false),
            Task(id = 5, name = "🇯🇵 Japon", isDone = false)
        )
    )

    // Cette variable permet au ViewModel de lire la liste
    val tasks: StateFlow<List<Task>> = _tasks

    // Fonction pour ajouter un pays manuellement via l'interface
    fun addTask(name: String) {
        val newId = (_tasks.value.maxOfOrNull { it.id } ?: 0) + 1
        val newTask = Task(id = newId, name = name)
        _tasks.value = _tasks.value + newTask
    }

    // Fonction pour cocher/décocher (Visité ou non)
    fun toggleTask(taskId: Int) {
        _tasks.value = _tasks.value.map {
            if (it.id == taskId) it.copy(isDone = !it.isDone) else it
        }
    }
}