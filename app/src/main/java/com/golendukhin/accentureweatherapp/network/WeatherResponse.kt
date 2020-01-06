package com.golendukhin.accentureweatherapp.network

import com.squareup.moshi.Json

data class WeatherResponse (
    @Json(name = "name") val city: String,
    val wind: Wind,
    val main: Main,
    val sys: Sys,
    val visibility: Int,
    @Json(name = "timezone") val timeZone: Long
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