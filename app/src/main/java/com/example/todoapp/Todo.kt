package com.example.todoapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todo")
data class Todo (
    val title : String,
    val timestamp : String,
    var isChecked : Boolean,
    @PrimaryKey(autoGenerate = true) var id : Int = 0
)