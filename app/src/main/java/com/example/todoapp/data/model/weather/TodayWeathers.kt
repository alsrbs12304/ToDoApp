package com.test.myscheduleapp.model.weather

import java.io.Serializable

class TodayWeathers:Serializable {
    var timeNow : String? = null
    var descWeather : String? = null
    var currentTemp = 0.0
    var tempMax = 0.0
    var tempMin = 0.0
}