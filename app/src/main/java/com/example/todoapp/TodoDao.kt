package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getList() : LiveData<MutableList<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: Todo)
}