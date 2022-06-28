package com.example.todoapp

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.todoapp.databinding.FragmentAddTaskBinding
import com.example.todoapp.databinding.FragmentTaskBinding
import com.example.todoapp.decorator.OneDayDecorator
import com.example.todoapp.decorator.SaturdayDecorator
import com.example.todoapp.decorator.SundayDecorator
import com.michaldrabik.classicmaterialtimepicker.CmtpDialogFragment
import com.michaldrabik.classicmaterialtimepicker.model.CmtpTime12
import com.michaldrabik.classicmaterialtimepicker.utilities.setOnTime12PickedListener
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddTaskFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    private var _binding : FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    private var year : Int = 0
    private var month : Int = 0
    private var day : Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            this.year = date.year
            this.month = date.month + 1
            this.day = date.day
            val taskDate = this.year.toString()+". "+this.month.toString()+". "+this.day.toString()+"."
            binding.taskDate.text = taskDate
        }

        binding.taskTime.setOnClickListener {
            val timePicker = CmtpDialogFragment.newInstance()
            timePicker.setInitialTime12(5, 15, CmtpTime12.PmAm.PM)
            fragmentManager?.let { it1 -> timePicker.show(it1, "TimePickerTag") }
            timePicker.setOnTime12PickedListener { time12 ->
                binding.taskTimeHour.text = time12.hour.toString()
                binding.taskTimeMinute.text = time12.minute.toString()
                binding.taskTimeAmpm.text = time12.pmAm.toString()
            }
        }


        val newTodo = Todo("dd", "dd", false)

        binding.addBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val db = TodoDatabase.getInstance(mainActivity)
                db!!.todoDao().insert(newTodo)
            }
        }
    }

    private fun initView(){
        binding.calendarView.selectedDate = CalendarDay.today()
        binding.calendarView.addDecorators(SaturdayDecorator(), SundayDecorator(), OneDayDecorator())

        this.year = CalendarDay.today().year
        this.month = CalendarDay.today().month + 1
        this.day = CalendarDay.today().day
        val taskDate = this.year.toString()+". "+this.month.toString()+". "+this.day.toString()+"."
        binding.taskDate.text = taskDate
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}