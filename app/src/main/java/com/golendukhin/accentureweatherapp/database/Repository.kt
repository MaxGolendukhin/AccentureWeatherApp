package com.golendukhin.accentureweatherapp.database

import androidx.lifecycle.LiveData
import com.golendukhin.accentureweatherapp.ResponseStatus
import com.golendukhin.accentureweatherapp.network.WeatherApi
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.text.SimpleDateFormat

class Repository(private val weatherDao: WeatherDao) {
    val weatherData: LiveData<List<Weather>> = weatherDao.getOrderedData()

    fun update(apiKey: String, city: String?): ResponseStatus {
        var responseStatus = ResponseStatus.OK
        runBlocking {
            try {
                val weatherFetched = WeatherApi.retrofitService.getWeather(q = city ?: "Riga", appid = apiKey)
                if (weatherFetched.isSuccessful) {
                    val body = weatherFetched.body()
                    val currentDate = SimpleDateFormat.getDateInstance().format(System.currentTimeMillis()).toString()
                    val lastWeather = weatherDao.getLastItem()

                    var dateInMillis = 0L
                    if (lastWeather != null) {
                        dateInMillis = lastWeather.saveDate
                    }
                    val lastDate: String? = SimpleDateFormat.getDateInstance().format(dateInMillis).toString()

                    val newWeather = Weather(
                        city = body?.city,
                        windSpeed = body?.wind?.speed,
                        temperatureCelsius = body?.main?.temperature,
                        feelsLike = body?.main?.feelsLike,
                        pressure = body?.main?.pressure,
                        humidity = body?.main?.humidity,
                        temperatureMax = body?.main?.temperatureMax,
                        temperatureMin = body?.main?.temperatureMin,
                        sunrise = body?.sys?.sunrise,
                        sunset = body?.sys?.sunset,
                        visibility = body?.visibility,
                        timeZone = body?.timeZone
                    )

                    if (currentDate == lastDate) {
                        newWeather.id = lastWeather.id
                        weatherDao.update(newWeather)
                    }
                    else weatherDao.insert(newWeather)
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