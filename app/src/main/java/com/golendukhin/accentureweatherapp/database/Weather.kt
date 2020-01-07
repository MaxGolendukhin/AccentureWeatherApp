package com.golendukhin.accentureweatherapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class Weather(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    val city: String?,
    @ColumnInfo(name = "wind_speed")val windSpeed: Double?,
    @ColumnInfo(name = "temperature_Celsius") var temperatureCelsius: Double?,
    @ColumnInfo(name = "feels_like") var feelsLike: Double?,
    val pressure: Double?,
    val humidity: Double?,
    @ColumnInfo(name = "temp_max") val temperatureMax: Double?,
    @ColumnInfo(name = "temp_min") val temperatureMin: Double?,
    val sunrise: Long?,
    val sunset: Long?,
    val visibility: Int?,
    @ColumnInfo(name = "save_date") val saveDate: Long = System.currentTimeMillis(),
    val timeZone: Long?
)