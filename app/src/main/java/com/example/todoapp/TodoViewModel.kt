package com.example.todoapp

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    val todoList : LiveData<MutableList<Todo>> = repository.getAll()


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

    class Factory(private val application : Application) : ViewModelProvider.Factory { // factory pattern
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TodoViewModel(TodoRepository.getInstance(application)!!) as T
        }
    }
}