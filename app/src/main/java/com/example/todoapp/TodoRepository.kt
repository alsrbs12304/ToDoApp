package com.example.todoapp

import android.app.Application
import androidx.lifecycle.LiveData

class TodoRepository(application: Application) {

    private val todoDao = TodoDatabase.getInstance(application)!!.todoDao()

    fun getAll() : LiveData<MutableList<Todo>>{
        return todoDao.getList()
    }

    suspend fun insert(todo : Todo){
        todoDao.insert(todo)
    }

    suspend fun delete(){
        todoDao.deleteAll()
    }

    companion object {
        private var instance: TodoRepository? = null

        fun getInstance(application : Application): TodoRepository? { // singleton pattern
            if (instance == null) instance = TodoRepository(application)
            return instance
        }
    }
}