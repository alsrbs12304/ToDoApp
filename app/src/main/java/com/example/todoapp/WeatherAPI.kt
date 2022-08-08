package com.example.todoapp

import com.test.myscheduleapp.model.weather.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather")
    fun getCurrentWeatherData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String = BuildConfig.API_KEY
    ) : Call<WeatherResponse>
    @GET("forecast")
    fun getTodayWeathers(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String = BuildConfig.API_KEY
    ) : Call<WeatherResponse>
}