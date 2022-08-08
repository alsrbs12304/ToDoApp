package com.example.todoapp.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.view.util.RecyclerViewDecorator
import com.example.todoapp.data.TodoAdapter
import com.example.todoapp.viewmodel.TodoViewModel
import com.example.todoapp.databinding.FragmentTaskBinding
import com.example.todoapp.view.util.OneDayDecorator
import com.example.todoapp.view.util.SaturdayDecorator
import com.example.todoapp.view.util.SundayDecorator
import com.example.todoapp.view.base.BaseFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

            todoViewModel.getTodo(year,month,day).observe(viewLifecycleOwner) {
                todoAdapter.setData(it)
                if (it.isEmpty()) {
                    binding.emptyList.visibility = View.VISIBLE
                } else {
                    binding.emptyList.visibility = View.INVISIBLE
                }
            }
        }

        todoAdapter.setItemCheckBoxClickListener(object : TodoAdapter.ItemCheckBoxClickListener{
            override fun onClick(view: View, position: Int, itemId: Int) {
                CoroutineScope(Dispatchers.IO).launch {
                    val todo = todoViewModel.getOne(itemId)
                    todo.isChecked = !todo.isChecked
                    todoViewModel.update(todo)
                }
            }
        })

        todoAdapter.setItemClickListener(object : TodoAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int, itemId: Int) {
                CoroutineScope(Dispatchers.IO).launch {
                    val todo = todoViewModel.getOne(itemId)
                    todoViewModel.delete(todo)
                }
            }
        })
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