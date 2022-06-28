package com.example.todoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemTodoBinding

class TodoAdapter(private val context : Context) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    var todoList = mutableListOf<Todo>()

    class ViewHolder(val binding : ItemTodoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(todo: Todo){
            binding.todo = todo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.ViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}