package com.example.todoapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp.data.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getList() : LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Query("DELETE FROM todo")
    suspend fun deleteAll()

    @Query("SELECT * FROM todo WHERE year = :year AND month = :month AND day = :day ORDER BY id DESC")
    fun getTodo(year : String, month : String, day : String) : LiveData<List<Todo>>
}