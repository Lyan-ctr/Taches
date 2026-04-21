package com.example.todolist.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todolist.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class) // Utilise le nom de la classe dans Converters.kt
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                )
                .fallbackToDestructiveMigration() // Évite les crashs si vous changez le schéma
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
