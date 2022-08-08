package com.example.todoapp.view.util

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*

class StatisticsWeekDayDecorator : DayViewDecorator {

    private val cal = Calendar.getInstance()

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        day?.copyTo(cal)
        val weekDay = cal.get(Calendar.DAY_OF_WEEK)
        return weekDay == Calendar.MONDAY || weekDay == Calendar.TUESDAY ||
                weekDay == Calendar.WEDNESDAY || weekDay == Calendar.THURSDAY ||
                weekDay == Calendar.FRIDAY
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(ForegroundColorSpan(Color.WHITE))
    }
}