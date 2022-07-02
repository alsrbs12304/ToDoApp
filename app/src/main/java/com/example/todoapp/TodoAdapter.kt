package com.example.todoapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.example.todoapp.databinding.ItemTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private var todoList : List<Todo> = ArrayList()

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

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList : List<Todo>){
        todoList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding : ItemTodoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(todo: Todo){
            binding.todo = todo
        }
    }
}
