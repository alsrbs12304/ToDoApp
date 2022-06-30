package com.example.todoapp

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val todoList : LiveData<MutableList<Todo>>
    private val repository : TodoRepository

    init {
        val todoDao = AppDatabase.getDatabase(application)!!.todoDao()
        repository = TodoRepository(todoDao)
        todoList = repository.todoList
    }

    fun insert(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(todo)
        }
    }

    fun delete(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete()
        }
    }
}