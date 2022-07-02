package com.example.todoapp

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    private val todoViewModel: TodoViewModel by lazy {
        ViewModelProvider(this, TodoViewModel.Factory(mainActivity.application))[TodoViewModel::class.java]
    }

    private var year: String? = null
    private var month: String? = null
    private var day: String? = null
    private var hour: String? = null
    private var minute: String? = null
    private var ampm: String? = null

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
            this.year = date.year.toString()
            this.month = (date.month + 1).toString()
            this.day = date.day.toString()
            val taskDate = this.year + ". " + this.month + ". " + this.day + "."
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
                this.hour = time12.hour.toString()
                this.minute = time12.minute.toString()
                this.ampm = time12.pmAm.toString()

            }
        }

        binding.deleteBtn.setOnClickListener {
            todoViewModel.delete()
        }

        binding.addBtn.setOnClickListener {
            val title: String = binding.taskTitle.text.toString()
            val year: String = this.year.toString()
            val month: String = this.month.toString()
            val day: String = this.day.toString()
            val hour: String = this.hour.toString()
            val minute: String = this.minute.toString()
            val ampm: String = this.ampm.toString()

            val newTodo = Todo(title, year, month, day, hour, minute, ampm, false)

            if (binding.taskTitle.text.isNotEmpty()) {
                todoViewModel.insert(newTodo)
                Toast.makeText(mainActivity, "저장되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(mainActivity, "할 일을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initView() {
        binding.calendarView.selectedDate = CalendarDay.today()
        binding.calendarView.addDecorators(
            SaturdayDecorator(),
            SundayDecorator(),
            OneDayDecorator()
        )

        this.year = CalendarDay.today().year.toString()
        this.month = (CalendarDay.today().month + 1).toString()
        this.day = CalendarDay.today().day.toString()
        val taskDate = this.year + ". " + this.month + ". " + this.day + "."
        binding.taskDate.text = taskDate
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}