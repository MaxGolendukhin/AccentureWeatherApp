package com.golendukhin.accentureweatherapp.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.golendukhin.accentureweatherapp.ResponseStatus
import com.golendukhin.accentureweatherapp.database.Repository
import com.golendukhin.accentureweatherapp.database.Weather
import com.golendukhin.accentureweatherapp.database.WeatherDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WeatherListViewModel(application: Application) : AndroidViewModel(application)  {
    private val repository: Repository
//    private var viewModelJob = Job()
//    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val weatherData: LiveData<List<Weather>>

    init {
        val weatherDao = WeatherDataBase.getInstance(application, viewModelScope).weatherDao
        repository = Repository(weatherDao)
        weatherData = repository.weatherData
    }

    fun getData(): LiveData<List<Weather>> {
        return weatherData
    }

//    override fun onCleared() {
//        super.onCleared()
//        viewModelJob.cancel()
//    }

    fun update(apiKey: String, city: String?): ResponseStatus {
        return repository.update(apiKey, city)
    }

    private val _navigateToDetails = MutableLiveData<Long>()
    val navigateToDetails: LiveData<Long>
        get() = _navigateToDetails

    fun displayDetails(id: Long) {
        _navigateToDetails.value = id
    }

    fun displayDetailsComplete() {
        _navigateToDetails.value = null
    }


//    private val _navigateToPreferences = MutableLiveData<String>()
//    val navigateToPreferences: LiveData<String>
//        get() = _navigateToPreferences
//
//    fun displayPreferences(city: String) {
//        _navigateToPreferences.value = city
//    }
//
//    fun displayPreferencesComplete() {
//        _navigateToPreferences.value = null
//    }
}