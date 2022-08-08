package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.todoapp.data.model.Todo
import com.example.todoapp.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    val todoList : LiveData<List<Todo>> = repository.getAll()

    fun insert(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(todo)
        }
    }

    fun delete(todo : Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(todo)
        }
    }

    fun update(todo : Todo){
        viewModelScope.launch(Dispatchers.IO){
            repository.update(todo)
        }
    }

    fun getTodo(year : String, month : String, day : String) : LiveData<List<Todo>> {
        return repository.getTodo(year, month, day)
    }

    fun getOne(id : Int) = repository.getOne(id)

    fun getDateData(year : Int, month : Int, day : Int) : LiveData<List<Todo>>{
        return repository.getDateData(year, month, day).asLiveData()
    }

    fun getCheckedData(year: Int,month: Int,day: Int): LiveData<Int> {
        return repository.getCheckedData(year, month, day)
    }

    class Factory(private val application : Application) : ViewModelProvider.Factory { // factory pattern
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TodoViewModel(TodoRepository.getInstance(application)!!) as T
        }
    }
}