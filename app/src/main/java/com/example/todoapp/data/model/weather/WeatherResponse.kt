package com.test.myscheduleapp.model.weather

import com.google.gson.annotations.SerializedName
import com.test.myscheduleapp.model.weather.Main
import com.test.myscheduleapp.model.weather.Weather

class WeatherResponse {
    @SerializedName("weather") var weather = ArrayList<Weather>()
    @SerializedName("main") var main: Main? = null
    @SerializedName("name") var city : String? = null
}