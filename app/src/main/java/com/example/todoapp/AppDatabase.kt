package com.example.todoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao

    companion object{
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase?{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "todo-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}