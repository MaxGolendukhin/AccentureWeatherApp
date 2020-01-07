package com.golendukhin.accentureweatherapp.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Weather::class], version = 1, exportSchema = false)
abstract class WeatherDataBase : RoomDatabase() {
    abstract val weatherDao : WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDataBase? = null

        fun getInstance(context: Context, scope: CoroutineScope): WeatherDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDataBase::class.java,
                    "weather_database"
                )
//                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
//                        populateDatabase(database.weatherDao)
                    }
                }
            }
        }

        /**
         * Populate the database in fragment_preferences new coroutine.
         * If you want to start with more words, just add them.
         */
//        suspend fun populateDatabase(weatherDao: WeatherDao) {
//            Log.i("Database", "populated")
//            weatherDao.deleteAll()
//            val weather = Weather(city = "Khabarovsk", windSpeed = 3.0, temperatureCelsius = -15.0,
//                feelsLike = -22.5, pressure = 956.0, humidity = 95.0, temperatureMax = -10.0,
//                temperatureMin = -18.0, sunrise, sunset, visibility, timeZone = )
//            weatherDao.insert(Weather((city = "Khabarovsk"
//                    ,)))
//        }
    }
}