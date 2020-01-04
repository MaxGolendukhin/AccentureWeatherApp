package com.golendukhin.accentureweatherapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@Entity(tableName = "weather_table")
data class Weather(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "save_date")
    var saveDate: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "temperature_Celsius")
    var temperatureCelsius: Int = 0
)
//{
//    fun getLocaleDate(): String = Date(saveDate).toLocaleString()
//}