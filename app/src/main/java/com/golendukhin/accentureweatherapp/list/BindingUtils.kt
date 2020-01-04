package com.golendukhin.accentureweatherapp.list

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.golendukhin.accentureweatherapp.database.Weather
import java.util.*

@BindingAdapter("localDateFormatted")
fun TextView.setLocalDate(item: Weather?) {
    item?.let {
        text = Date(it.saveDate).toLocaleString()
    }
}

@BindingAdapter("temperatueFormatted")
fun TextView.setCelsius(item: Weather?) {
    item?.let {
        text = "${it.temperatureCelsius}\u2109 C"
    }
}