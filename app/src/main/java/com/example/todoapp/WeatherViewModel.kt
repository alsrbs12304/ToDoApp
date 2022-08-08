package com.example.todoapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.RetrofitClient
import com.test.myscheduleapp.model.weather.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {
    val weatherList : MutableLiveData<WeatherResponse> = MutableLiveData()
    val todayWeatherList : MutableLiveData<WeatherResponse> =MutableLiveData()

    fun getCurrentWeatherData(lat:String, lon:String){
        val service = RetrofitClient.service.getCurrentWeatherData(lat, lon)
        service.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                weatherList.value = response.body()
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("FAIL", "FAIL")
            }
        })
    }
    fun getTodayWeathers(lat:String, lon: String){
        val service = RetrofitClient.service.getTodayWeathers(lat, lon)
        service.enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                todayWeatherList.value = response.body()
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("FAIL", "FAIL")
            }
        })
    }
}