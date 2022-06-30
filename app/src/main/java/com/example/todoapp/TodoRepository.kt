package com.example.todoapp

import android.app.Application
import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {

    val todoList : LiveData<MutableList<Todo>> = todoDao.getList()

    suspend fun insert(todo : Todo){
        todoDao.insert(todo)
    }

    suspend fun delete(){
        todoDao.deleteAll()
    }
}