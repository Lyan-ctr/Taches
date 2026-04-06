package com.example.todolist.model

data class Task(
    val id: Int,
    val name :String,
    val isDone : Boolean = false,
)
