package com.example.todoapp

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao : TodoDao) {

    val getList : LiveData<MutableList<Todo>> = todoDao.getList()

    fun insert(todo : Todo){
        todoDao.insert(todo)
    }
}