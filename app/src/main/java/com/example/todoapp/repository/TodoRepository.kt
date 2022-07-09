package com.example.todoapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todoapp.data.model.Todo
import com.example.todoapp.data.TodoDatabase

class TodoRepository(application: Application) {

    private val todoDao = TodoDatabase.getInstance(application)!!.todoDao()

    fun getAll() : LiveData<List<Todo>>{
        return todoDao.getList()
    }

    suspend fun insert(todo : Todo){
        todoDao.insert(todo)
    }

    suspend fun delete(todo : Todo) = todoDao.delete(todo)

    suspend fun update(todo: Todo) = todoDao.update(todo)

    fun getTodo(year : String, month : String, day : String) : LiveData<List<Todo>> {
        return todoDao.getTodo(year, month, day)
    }

    fun getOne(id: Int) : Todo = todoDao.selectOne(id)

    companion object {
        private var instance: TodoRepository? = null

        fun getInstance(application : Application): TodoRepository? { // singleton pattern
            if (instance == null) instance = TodoRepository(application)
            return instance
        }
    }
}