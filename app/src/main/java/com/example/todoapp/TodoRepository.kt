package com.example.todoapp

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao : TodoDao) {

    val readAllData : LiveData<MutableList<Todo>> = todoDao.getList()

    suspend fun insert(todo : Todo){
        todoDao.insert(todo)
    }
}