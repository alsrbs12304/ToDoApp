package com.example.todoapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp.data.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getList() : LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun delete(todo : Todo)

    @Update
    suspend fun update(todo: Todo)

    @Query("SELECT * FROM todo WHERE year = :year AND month = :month AND day = :day ORDER BY id DESC")
    fun getTodo(year : String, month : String, day : String) : LiveData<List<Todo>>

    @Query("SELECT * FROM Todo WHERE year = :year AND month = :month AND day = :day ORDER BY id DESC")
    fun getDateData(year : Int, month : Int, day : Int) : Flow<List<Todo>>

    // 일정 중 완료한(Check 된) 일정의 개수 가져오기
    @Query("SELECT COUNT(*) FROM Todo WHERE year = :year AND month = :month AND day = :day AND `isChecked` = 1")
    fun getCheckedData(year: Int, month: Int, day: Int) : LiveData<Int>

    @Query("select * from todo where id = (:id)")
    fun selectOne(id: Int): Todo
}