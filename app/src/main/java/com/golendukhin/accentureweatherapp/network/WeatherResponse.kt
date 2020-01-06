package com.golendukhin.accentureweatherapp.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class WeatherResponse (
    val name: String,
    val wind: Wind,
    val main: Main,
    val sys: Sys,
    val visibility: Integer,
    val weather: List<Weather>
)

data class Sys (
    val sunrise: Long,
    val sunset: Long
)

data class Main (
    @Json(name = "temp") val temperature: Double,
    @Json(name = "feels_like") val feelsLike: Double?,
    val pressure: Double,
    val humidity: Double,
    @Json(name = "temp_max") val temperatureMax: Double,
    @Json(name = "temp_min") val temperatureMin: Double
)

data class Wind (
    val speed: Double
)

data class Weather (
    val main: String,
    val description: String
)