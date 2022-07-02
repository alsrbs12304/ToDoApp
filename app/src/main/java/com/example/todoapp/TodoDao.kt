package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

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