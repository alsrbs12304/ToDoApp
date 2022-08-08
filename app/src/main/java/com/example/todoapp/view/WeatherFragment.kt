package com.example.todoapp.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.*
import com.example.todoapp.databinding.FragmentWeatherBinding
import com.test.myscheduleapp.model.weather.TodayWeathers
import org.json.JSONException
import org.json.JSONObject
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import androidx.lifecycle.Observer
import com.androidnetworking.AndroidNetworking


class WeatherFragment : Fragment() {

    private var binding: FragmentWeatherBinding? = null
    lateinit var mainActivity: MainActivity
    private val instance = Calendar.getInstance()
    private var weatherAdapter : WeatherAdapter? = null
    private val todayWeathers: MutableList<TodayWeathers> = ArrayList()
    private var lat = "37.2342"
    private var lon = "127.2064"

    private lateinit var viewModel : WeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        getToday()
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherAdapter = WeatherAdapter(todayWeathers)
        with(binding!!){
            with(weatherRv){
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                adapter = weatherAdapter
            }
        }

        val dialog = NextDaysFragment()
        binding!!.nextDaysBtn.setOnClickListener {
            fragmentManager?.let { it1 -> dialog.show(it1,dialog.tag) }
        }

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        viewModel.getCurrentWeatherData(lat, lon)
        viewModel.weatherList.observe(viewLifecycleOwner, Observer {
            binding!!.cityTv.text = it.city
            binding!!.cTempTv.text = (it.main!!.temp - 273.15).toInt().toString()
            binding!!.conditionTv.text = getSky(it.weather[0].id)
        })
    }

    private fun getToday() {
        val date = Calendar.getInstance().time
        val tanggal = DateFormat.format("yyyy년 MM월 dd일", date) as String
        val formatDate = "$tanggal ${getDate(instance.get(Calendar.DAY_OF_WEEK))}"
        binding!!.dateTv.text = formatDate
    }

    private fun getSky(condition : Int) : String {
        return when (condition) {
            800 -> {
                return "맑음"
            }
            in 801..804 -> {
                return "흐림"
            }
            in 500..599 -> {
                return "비"
            }
            in 600..700 -> {
                return "눈"
            }
            in 200..299 -> {
                return "번개"
            }
            in 300..499 -> {
                return "이슬비"
            }
            in 701..799 -> {
                return "안개"
            }
            else -> "오류 rainType : $condition"
        }
    }

    private fun getDate(num : Int) : String{
        return when (num) {
            1 -> {
                return "일요일"
            }
            2 -> {
                return "월요일"
            }
            3-> {
                return "화요일"
            }
            4-> {
                return "수요일"
            }
            5 -> {
                return "목요일"
            }
            6-> {
                return "금요일"
            }
            7-> {
                return "토요일"
            }
            else -> "null"
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}