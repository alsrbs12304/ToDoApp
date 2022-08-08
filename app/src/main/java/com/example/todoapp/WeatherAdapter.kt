package com.example.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.myscheduleapp.model.weather.TodayWeathers
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.example.todoapp.databinding.CardLayoutBinding
import java.util.*

class WeatherAdapter(private val items: List<TodayWeathers>): RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    class ViewHolder(val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(CardLayoutBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]

        // cardView 랜덤 색 적용
        val generator = ColorGenerator.MATERIAL
        val color = generator.randomColor
        holder.binding.cardView.setCardBackgroundColor(color)

        holder.binding.time.text = data.timeNow
        holder.binding.currentTemp.text = String.format(Locale.getDefault(), "%.0f°C", data.currentTemp - 273.15)
        holder.binding.tempMin.text = String.format(Locale.getDefault(), "%.0f°C", data.tempMin - 273.15)
        holder.binding.tempMax.text = String.format(Locale.getDefault(), "%.0f°C", data.tempMax - 273.15)


        when (data.descWeather) {
            "broken clouds" -> {
                holder.binding.iconTemp.setAnimation(R.raw.broken_clouds)
            }
            "light rain" -> {
                holder.binding.iconTemp.setAnimation(R.raw.light_rain)
            }
            "overcast clouds" -> {
                holder.binding.iconTemp.setAnimation(R.raw.overcast_clouds)
            }
            "moderate rain" -> {
                holder.binding.iconTemp.setAnimation(R.raw.moderate_rain)
            }
            "few clouds" -> {
                holder.binding.iconTemp.setAnimation(R.raw.few_clouds)
            }
            "heavy intensity rain" -> {
                holder.binding.iconTemp.setAnimation(R.raw.heavy_intentsity)
            }
            "clear sky" -> {
                holder.binding.iconTemp.setAnimation(R.raw.clear_sky)
            }
            "scattered clouds" -> {
                holder.binding.iconTemp.setAnimation(R.raw.scattered_clouds)
            }
            else -> {
                holder.binding.iconTemp.setAnimation(R.raw.unknown)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}