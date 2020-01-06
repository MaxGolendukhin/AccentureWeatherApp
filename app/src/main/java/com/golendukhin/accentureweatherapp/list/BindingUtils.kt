package com.golendukhin.accentureweatherapp.list

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.golendukhin.accentureweatherapp.database.Weather
import java.text.SimpleDateFormat

@BindingAdapter("localDateFormatted")
fun TextView.setLocalDate(item: Weather?) {
    item?.let {
        text = SimpleDateFormat.getDateInstance().format(it.saveDate)
    }
}

@BindingAdapter("temperatueFormatted")
fun TextView.setCelsius(item: Weather?) {
    item?.let {
        text = "${it.temperatureCelsius}\u2103"
    }
}