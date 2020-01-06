package com.golendukhin.accentureweatherapp.details

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.golendukhin.accentureweatherapp.database.Weather
import java.text.SimpleDateFormat
import kotlin.math.roundToInt

@BindingAdapter("localDateTimeFormatted")
fun TextView.setLocalDateTime(item: Weather?) {
    item?.let {
        text = SimpleDateFormat.getDateTimeInstance().format(it.saveDate)
    }
}

@BindingAdapter("sunriseFormatted")
fun TextView.setSunriseTime(weather: Weather?) {
    weather?.let {
        text = SimpleDateFormat.getTimeInstance().format(((weather?.sunrise ?: 0) + (weather?.timeZone ?: 0)) * 1000)
    }
}

@BindingAdapter("sunsetFormatted")
fun TextView.setSunsetTime(weather: Weather?) {
    weather?.let {
        text = SimpleDateFormat.getTimeInstance().format(((weather?.sunset ?: 0) + (weather?.timeZone ?: 0)) * 1000)
    }
}

@BindingAdapter("temperatureFormatted")
fun TextView.setCelsiusFormat(temperature: Double?) {
    temperature?.let {
        text = "${it.roundToInt()}\u2103"
    }
}

@BindingAdapter("formatHumidity")
fun TextView.setHumidityFormatted(humidity: Double?) {
    humidity?.let {
        text = "${it.roundToInt()}%"
    }
}

@BindingAdapter("formatWindSpeed")
fun TextView.setWindFormatted(windSpeed: Double?) {
    windSpeed?.let {
        text = "${it.roundToInt()} meters per second"
    }
}

@BindingAdapter("formatVisibility")
fun TextView.setVisibilityFormatted(visibility: Int?) {
    visibility?.let {
        text = "$it meters"
    }
}

@BindingAdapter("formatPressure")
fun TextView.setPressureFormatted(pressure: Double?) {
    pressure?.let {
        text = "${it.roundToInt()} mmHg"
    }
}