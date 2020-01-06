package com.golendukhin.accentureweatherapp.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.golendukhin.accentureweatherapp.database.Weather
import com.golendukhin.accentureweatherapp.databinding.WeatherItemBinding

class WeatherListFragmentAdapter(val weatherItemClickListener: WeatherItemClickListener) : ListAdapter<Weather, WeatherListFragmentAdapter.ViewHolder>(WeatherDiffCallBack()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(weatherItemClickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: WeatherItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weatherItemClickListener: WeatherItemClickListener, item: Weather) {
            binding.weather = item
            binding.clickListener = weatherItemClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = WeatherItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class WeatherDiffCallBack : DiffUtil.ItemCallback<Weather>() {
    override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem == newItem
    }
}

class WeatherItemClickListener(val clickListener: (id: Long) -> Unit) {
    fun onClick(weather: Weather) = clickListener(weather.id)
}