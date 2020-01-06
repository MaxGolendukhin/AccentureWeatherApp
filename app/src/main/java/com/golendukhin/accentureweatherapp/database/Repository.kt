package com.golendukhin.accentureweatherapp.database

import androidx.lifecycle.LiveData
import com.golendukhin.accentureweatherapp.ResponseStatus
import com.golendukhin.accentureweatherapp.network.WeatherApi
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.text.SimpleDateFormat

class Repository(private val weatherDao: WeatherDao) {
    val weatherData: LiveData<List<Weather>> = weatherDao.getOrderedData()

    fun insert(weather: Weather) {
        weatherDao.insert(weather)
    }

    fun update(apiKey: String, coroutineScope: CoroutineScope): ResponseStatus {
        var responseStatus = ResponseStatus.OK
        runBlocking {
            try {
                var weatherFetched = WeatherApi.retrofitService.getWeather(q = "Riga", appid = apiKey)
                if (weatherFetched.isSuccessful) {
                    val body = weatherFetched.body()
                    val currentDate = SimpleDateFormat.getDateInstance().format(System.currentTimeMillis()).toString()
                    val lastWeather = weatherDao.getLastItem()
                    val lastDate = SimpleDateFormat.getDateInstance().format(lastWeather?.saveDate).toString()

                    if (currentDate == lastDate)
                        weatherDao.update(Weather(id = lastWeather.id, temperatureCelsius = body?.main?.temperature))
                    else
                       insert(Weather(temperatureCelsius = body?.main?.temperature))
                }
            } catch (e: HttpException) {
                responseStatus =  ResponseStatus.HTTP_CONNECTION_ERROR

            } catch (e: Throwable) {
                responseStatus =  ResponseStatus.ANOTHER_ERROR
            }
        }
        return responseStatus
    }

    fun getItemById(id: Long): LiveData<Weather> {
        return weatherDao.getItemById(id)
    }
}