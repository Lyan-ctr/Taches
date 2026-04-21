package com.example.todolist.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.model.Task
import kotlinx.coroutines.flow.Flow // On garde uniquement celui-ci

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY dateFin ASC")
    fun getAllTasks(): Flow<List<Task>>

    // NOUVEAU (Pour le TP) : Récupérer une tâche spécifique selon son ID
    @Query("SELECT * FROM task WHERE id = :taskId LIMIT 1")
    fun getTaskById(taskId: Int): Flow<Task?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}