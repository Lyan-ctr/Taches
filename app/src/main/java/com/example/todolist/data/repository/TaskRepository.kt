package com.example.todolist.data.repository

import com.example.todolist.local.TaskDao
import com.example.todolist.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TaskRepository(private val taskDao: TaskDao) {

    val tasks: Flow<List<Task>> = taskDao.getAllTasks()

    fun getTaskById(taskId: Int): Flow<Task?> {
        return taskDao.getTaskById(taskId)
    }

    suspend fun addTask(name: String, dateDebut: LocalDate, dateFin: LocalDate) {
        val task = Task(name = name, dateDebut = dateDebut, dateFin = dateFin)
        taskDao.insertTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}
