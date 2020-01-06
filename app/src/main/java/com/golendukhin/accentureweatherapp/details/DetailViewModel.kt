package com.golendukhin.accentureweatherapp.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.golendukhin.accentureweatherapp.database.Repository
import com.golendukhin.accentureweatherapp.database.Weather
import com.golendukhin.accentureweatherapp.database.WeatherDataBase

class DetailViewModel(id: Long, application: Application) : AndroidViewModel(application)  {
    private val repository: Repository

   val weather: LiveData<Weather>

    init {
        val weatherDao = WeatherDataBase.getInstance(application, viewModelScope).weatherDao
        repository = Repository(weatherDao)
        weather = repository.getItemById(id)
    }
}