package com.example.todoapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todo")
data class Todo (
    val title : String,
    val year : String,
    val month : String,
    val day : String,
    val hour : String,
    val minute : String,
    val ampm : String,
    var isChecked : Boolean,
    @PrimaryKey(autoGenerate = true) var id : Int = 0
)