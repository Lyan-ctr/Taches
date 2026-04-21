package com.example.todolist.local
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    object LocalDateConverter {
        private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        @TypeConverter
        fun fromLocalDate(date: LocalDate?): String? {
            return date?.format(formatter)
        }

        @TypeConverter
        fun toLocalDate(dateString: String?): LocalDate? {
            return dateString?.let { LocalDate.parse(it, formatter) }
        }
    }
}