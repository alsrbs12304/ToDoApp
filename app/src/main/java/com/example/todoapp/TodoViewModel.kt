package com.example.todoapp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : ViewModel() {

    private val getAll : LiveData<MutableList<Todo>>
    private val repository : TodoRepository

    init {
        val todoDao = TodoDatabase.getInstance(application)!!.todoDao()
        repository = TodoRepository(todoDao)
        getAll = repository.getList
    }

    fun insert(todo : Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(todo)
        }
    }
}