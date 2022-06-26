package com.example.todoapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "TodoTable")
class Todo (
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id : Int = 0,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "timestamp") val timestamp : String,
    @ColumnInfo(name = "isChecked") var isChecked : Boolean
):Serializable{
}