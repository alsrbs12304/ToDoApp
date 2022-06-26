package com.example.todoapp.decorator

import android.graphics.Color
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.graphics.Typeface
import android.text.style.StyleSpan
import java.util.*


class OneDayDecorator : DayViewDecorator {
    private var date = CalendarDay.today()
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.equals(date)!!
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(StyleSpan(Typeface.BOLD))
        view.addSpan(RelativeSizeSpan(1.4f))
    }
    fun setDate(date: Date?) {
        this.date = CalendarDay.from(date)
    }
}