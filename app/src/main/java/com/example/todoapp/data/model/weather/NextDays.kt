package com.test.myscheduleapp.model.weather

import java.io.Serializable

class NextDays : Serializable {
    var nameDate: String? = null
    var descWeather: String? = null
    var tempMax = 0.0
    var tempMin = 0.0
}