package com.example.todoapp.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.decorator.RecyclerViewDecorator
import com.example.todoapp.data.TodoAdapter
import com.example.todoapp.databinding.FragmentAddTaskBinding
import com.example.todoapp.viewmodel.TodoViewModel
import com.example.todoapp.databinding.FragmentTaskBinding
import com.example.todoapp.decorator.OneDayDecorator
import com.example.todoapp.decorator.SaturdayDecorator
import com.example.todoapp.decorator.SundayDecorator
import com.example.todoapp.view.base.BaseFragment
import com.prolificinteractive.materialcalendarview.CalendarDay

class TaskFragment  : BaseFragment<FragmentTaskBinding>(R.layout.fragment_task) {

    lateinit var navController: NavController

    private var year: String = ""
    private var month: String = ""
    private var day: String = ""
    private var hour: String = ""
    private var minute: String = ""
    private var ampm: String = ""

    private val todoViewModel: TodoViewModel by lazy {
        ViewModelProvider(this, TodoViewModel.Factory(mainActivity.application))[TodoViewModel::class.java]
    }
    private lateinit var todoAdapter: TodoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCalendar()
        initRv()
        setObserver()

        binding.fabAddTask.setOnClickListener {
            navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_taskFragment_to_addTaskFragment)
        }

        binding.calendarView.setOnDateChangedListener { _, date, _ ->
            this.year = date.year.toString()
            this.month = (date.month+1).toString()
            this.day = date.day.toString()

            todoViewModel.getTodo(year,month,day).observe(viewLifecycleOwner, {
                todoAdapter.setData(it)
                if(it.isEmpty()){
                    binding.emptyList.visibility = View.VISIBLE
                }else{
                    binding.emptyList.visibility = View.INVISIBLE
                }
            })
        }
    }

    private fun initCalendar(){
        binding.calendarView.selectedDate = CalendarDay.today()
        binding.calendarView.addDecorators(SaturdayDecorator(), SundayDecorator(), OneDayDecorator())

        this.year = CalendarDay.today().year.toString()
        this.month = (CalendarDay.today().month + 1).toString()
        this.day = CalendarDay.today().day.toString()
    }
    private fun initRv(){
        todoAdapter = TodoAdapter().apply {
            setHasStableIds(true) // Recyclerview 업데이트 시 깜빡임 방지
        }
        binding.todoRv.apply {
            addItemDecoration(RecyclerViewDecorator(20))
            layoutManager = LinearLayoutManager(context)
            adapter = todoAdapter
        }
    }

    private fun setObserver(){
        todoViewModel.getTodo(year,month,day).observe(viewLifecycleOwner, {
            todoAdapter.setData(it)
            if(it.isEmpty()){
                binding.emptyList.visibility = View.VISIBLE
            }else{
                binding.emptyList.visibility = View.INVISIBLE
            }
        })
    }
}