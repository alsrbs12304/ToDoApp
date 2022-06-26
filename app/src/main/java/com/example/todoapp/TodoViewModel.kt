package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    private val todo : LiveData<MutableList<Todo>> = repository.readAllData

    fun insert(todo : Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(todo)
        }
    }
}