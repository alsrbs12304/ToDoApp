package com.example.todoapp.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentStatisticsBinding
import com.example.todoapp.view.base.BaseFragment
import com.example.todoapp.view.util.StatisticsWeekDayDecorator
import com.example.todoapp.view.util.StatisticsWeekEndDecorator
import com.example.todoapp.viewmodel.TodoViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.lang.ArithmeticException
import java.util.*

class StatisticsFragment : BaseFragment<FragmentStatisticsBinding>(R.layout.fragment_statistics) {

    private val todoViewModel: TodoViewModel by lazy {
        ViewModelProvider(this, TodoViewModel.Factory(mainActivity.application))[TodoViewModel::class.java]
    }
    private val cal = Calendar.getInstance()
    private var year : Int = 0
    private var month : Int = 0
    private var day : Int = 0

    private var allTask : Int = 0
    private var checkedDateTask : Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create 시 오늘 날짜 select & 요일 별 색상 Setting
        binding.calendarView.selectedDate = CalendarDay.today()
        binding.calendarView.addDecorator(StatisticsWeekDayDecorator())
        binding.calendarView.addDecorator(StatisticsWeekEndDecorator())

        year = cal.get(Calendar.YEAR)
        month = cal.get(Calendar.MONTH) + 1
        day = cal.get(Calendar.DATE)

        todoViewModel.getDateData(year, month, day).observe(viewLifecycleOwner, Observer{
            allTask = it.size
            binding.allTask.text = allTask.toString()
        })
        todoViewModel.getCheckedData(year,month,day).observe(viewLifecycleOwner, Observer {
            binding.checkedTask.text = it.toString()
            try {
                checkedDateTask = (it * 100) / allTask
                binding.progress.text = checkedDateTask.toString()
                binding.progressbar.progress = checkedDateTask
            }catch (e : ArithmeticException){
                binding.progress.text = "0"
                binding.progressbar.progress = 0
            }
        })

        binding.calendarView.setOnDateChangedListener { _, date, _ ->
            this.year = date.year
            this.month = date.month + 1
            this.day = date.day
            val clickedDate = this.year.toString()+". "+this.month.toString()+". "+this.day.toString()+"."
            binding.clickedDate.text = clickedDate

            todoViewModel.getDateData(this.year,this.month, this.day).observe(viewLifecycleOwner, Observer {
                allTask = it.size
                binding.allTask.text = allTask.toString()
            })
            todoViewModel.getCheckedData(this.year,this.month,this.day).observe(viewLifecycleOwner, Observer {
                binding.checkedTask.text = it.toString()
                try {
                    checkedDateTask = (it * 100) / allTask
                    binding.progress.text = checkedDateTask.toString()
                    binding.progressbar.progress = checkedDateTask
                }catch (e : ArithmeticException){
                    binding.progress.text = "0"
                    binding.progressbar.progress = 0
                }
            })
        }

    }
}