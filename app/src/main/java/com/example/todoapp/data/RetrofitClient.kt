package com.example.todoapp.data

import com.example.todoapp.BuildConfig
import com.example.todoapp.WeatherAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val service : WeatherAPI by lazy{
        retrofit.create(WeatherAPI::class.java)
    }
}