package com.example.todoapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.todoapp.databinding.FragmentNextDaysBinding
import com.example.todoapp.view.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.test.myscheduleapp.model.weather.NextDays
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NextDaysFragment : BottomSheetDialogFragment(){
    private var binding: FragmentNextDaysBinding? = null
    lateinit var mainActivity: MainActivity

    private var nextDayAdapter : NextDayAdapter? = null
    private var lat = "37.234704"
    private var lon = "127.284106"
    private var nextDays : MutableList<NextDays> = ArrayList()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle( // Background -> Transparent.
            STYLE_NORMAL,
            R.style.TransparentBottomSheetDialogFragment
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNextDaysBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nextDayAdapter = NextDayAdapter(nextDays)
        with(binding!!){
            with(nextDaysRv){
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                adapter = nextDayAdapter
            }
        }
        binding!!.fabClose.setOnClickListener {
            dismiss()
        }
        getNextDaysWeather()
    }

    override fun onDestroy() {
        super.onDestroy()
        nextDays.clear()
    }

    private fun getNextDaysWeather() {
        AndroidNetworking.get(BuildConfig.BASE_URL + "onecall?" + "lat=${lat}&lon=${lon}" + BuildConfig.UnitsAppidDaily)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
                override fun onResponse(response: JSONObject) {
                    try {
                        val jsonArray = response.getJSONArray("daily")
                        for (i in 0 until jsonArray.length()) {
                            val dataApi = NextDays()
                            val objectList = jsonArray.getJSONObject(i)
                            val jsonObjectOne = objectList.getJSONObject("temp")
                            val jsonArrayOne = objectList.getJSONArray("weather")
                            val jsonObjectTwo = jsonArrayOne.getJSONObject(0)
                            val longDate = objectList.optLong("dt")
                            val formatDate = SimpleDateFormat("dd일")
                            val readableDate = formatDate.format(Date(longDate * 1000))
                            val longDay = objectList.optLong("dt")
                            val format = SimpleDateFormat("EEEE")
                            val readableDay = format.format(Date(longDay * 1000))

                            dataApi.nameDate = "$readableDate $readableDay"
                            dataApi.descWeather = jsonObjectTwo.getString("description")
                            dataApi.tempMin = jsonObjectOne.getDouble("min")
                            dataApi.tempMax = jsonObjectOne.getDouble("max")
                            nextDays.add(dataApi)
                        }
                        nextDayAdapter?.notifyDataSetChanged()
                    } catch (e: JSONException) { e.printStackTrace() }
                }

                override fun onError(e: ANError) {
                    Toast.makeText(context, "인터넷에 연결할 수 없습니다!", Toast.LENGTH_SHORT).show()
                }
            })
    }
}