package com.golendukhin.accentureweatherapp.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.golendukhin.accentureweatherapp.database.WeatherDao
import com.golendukhin.accentureweatherapp.database.WeatherDataBase

class WeatherListViewModel(val weatherDao: WeatherDao, application: Application) : AndroidViewModel(application) {
}


