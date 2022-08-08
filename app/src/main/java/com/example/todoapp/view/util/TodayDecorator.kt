package com.example.todoapp.view.util

import android.annotation.SuppressLint
import android.content.Context
import com.example.todoapp.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class TodayDecorator(context: Context): DayViewDecorator {
    private var date = CalendarDay.today()
    @SuppressLint("UseCompatLoadingForDrawables")
    val drawable = context.resources.getDrawable(R.drawable.style_only_radius_10)
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.equals(date)!!
    }
    override fun decorate(view: DayViewFacade?) {
        view?.setBackgroundDrawable(drawable)
    }
}