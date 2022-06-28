package com.example.todoapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.FragmentTaskBinding
import com.example.todoapp.decorator.OneDayDecorator
import com.example.todoapp.decorator.SaturdayDecorator
import com.example.todoapp.decorator.SundayDecorator
import com.prolificinteractive.materialcalendarview.CalendarDay.today

class TaskFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    private var _binding : FragmentTaskBinding? = null
    private val binding get() = _binding!!

    lateinit var navController: NavController

    lateinit var todoAdapter: TodoAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCalendar()

        binding.fabAddTask.setOnClickListener {
            navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_taskFragment_to_addTaskFragment)
        }
        todoAdapter = TodoAdapter(mainActivity)
        binding.todoRecyclerview.adapter = todoAdapter
    }

    private fun initCalendar(){
        binding.calendarView.selectedDate = today()
        binding.calendarView.addDecorators(SaturdayDecorator(), SundayDecorator(), OneDayDecorator())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}