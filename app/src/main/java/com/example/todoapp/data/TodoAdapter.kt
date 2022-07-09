package com.example.todoapp.data

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.model.Todo
import com.example.todoapp.databinding.ItemTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private var todoList : List<Todo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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

    inner class ViewHolder(val binding : ItemTodoBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("ResourceAsColor")
        fun bind(todo: Todo){
            binding.todo = todo

            binding.checkbox.setOnClickListener {
                itemCheckBoxClickListener.onClick(it, layoutPosition, todoList[layoutPosition].id)
            }

            binding.deleteBtn.setOnClickListener {
                itemClickListener.onClick(it, layoutPosition, todoList[layoutPosition].id)
            }

            if(todo.isChecked) {
                binding.todoTitle.paintFlags = binding.todoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }else{
                binding.todoTitle.paintFlags = binding.todoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }

    interface ItemCheckBoxClickListener {
        fun onClick(view: View, position: Int, itemId: Int)
    }

    private lateinit var itemCheckBoxClickListener: ItemCheckBoxClickListener

    fun setItemCheckBoxClickListener(itemCheckBoxClickListener: ItemCheckBoxClickListener) {
        this.itemCheckBoxClickListener = itemCheckBoxClickListener
    }


    interface OnItemClickListener {
        fun onClick(v: View, position: Int, itemId: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    private lateinit var itemClickListener : OnItemClickListener
}
