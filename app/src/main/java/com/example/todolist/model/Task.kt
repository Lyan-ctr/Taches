package com.example.todolist.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val isDone: Boolean = false,
    val dateDebut: LocalDate,
    val dateFin: LocalDate
)
