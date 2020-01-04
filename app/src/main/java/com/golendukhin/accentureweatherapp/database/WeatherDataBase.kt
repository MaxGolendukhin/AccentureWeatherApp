package com.golendukhin.accentureweatherapp.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()
fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}

@Database(entities = [Weather::class], version = 1, exportSchema = false)
abstract class WeatherDataBase : RoomDatabase() {
    abstract val weatherDao : WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDataBase? = null

        fun getInstance(context: Context): WeatherDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, WeatherDataBase::class.java, "weather_database")
                        .addCallback(
                            seedDatabaseCallback(context)
                        )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        private fun seedDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)

                    Log.i("Database", "populated")
                    var weatherDao = getInstance(context).weatherDao
                    weatherDao.deleteAll()
                    weatherDao.insert(Weather(id = 0, temperatureCelsius = -5))
                    weatherDao.insert(Weather(id = 1, temperatureCelsius = -6))
                    weatherDao.insert(Weather(id = 2, temperatureCelsius = -10))
                    weatherDao.insert(Weather(id = 3, temperatureCelsius = -5))
                    weatherDao.insert(Weather(id = 4, temperatureCelsius = 0))
                    weatherDao.insert(Weather(id = 5, temperatureCelsius = 1))

                    ioThread {
                        Log.i("Database", "populated")
                        var weatherDao = getInstance(context).weatherDao
                        weatherDao.deleteAll()
                        weatherDao.insert(Weather(id = 0, temperatureCelsius = -5))
                        weatherDao.insert(Weather(id = 1, temperatureCelsius = -6))
                        weatherDao.insert(Weather(id = 2, temperatureCelsius = -10))
                        weatherDao.insert(Weather(id = 3, temperatureCelsius = -5))
                        weatherDao.insert(Weather(id = 4, temperatureCelsius = 0))
                        weatherDao.insert(Weather(id = 5, temperatureCelsius = 1))
                    }
                }
            }
        }
    }
}