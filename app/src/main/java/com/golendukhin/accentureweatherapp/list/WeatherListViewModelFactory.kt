package com.golendukhin.accentureweatherapp.list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.golendukhin.accentureweatherapp.database.WeatherDao

class WeatherListViewModelFactory(private val weatherDao: WeatherDao, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherListViewModel::class.java)) {
            return WeatherListViewModel(weatherDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}