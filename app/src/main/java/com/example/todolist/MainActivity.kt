package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.data.repository.TaskRepository
import com.example.todolist.local.TaskDatabase
import com.example.todolist.ui.navigation.NavigationGraph
import com.example.todolist.ui.theme.TodolistTheme
import com.example.todolist.viewmodel.TaskViewModel
import com.example.todolist.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialisation de la base de données, du repository et du ViewModel
        val database = TaskDatabase.getDatabase(this)
        val repository = TaskRepository(database.taskDao())
        val viewModelFactory = ViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[TaskViewModel::class.java]

        setContent {
            TodolistTheme {
                NavigationGraph(viewModel = viewModel)
            }
        }
    }
}
