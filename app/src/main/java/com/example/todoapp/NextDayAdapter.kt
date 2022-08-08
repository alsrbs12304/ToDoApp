package com.example.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemNextDaysListBinding

import com.test.myscheduleapp.model.weather.NextDays
import java.util.*

class NextDayAdapter( private val items: List<NextDays>) : RecyclerView.Adapter<NextDayAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemNextDaysListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_next_days_list, parent, false)
        return ViewHolder(ItemNextDaysListBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]


        holder.binding.txtDay.text = data.nameDate
        holder.binding.tempMin.text = String.format(Locale.getDefault(), "%.0f°C", data.tempMin)
        holder.binding.tempMax.text = String.format(Locale.getDefault(), "%.0f°C", data.tempMax)

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