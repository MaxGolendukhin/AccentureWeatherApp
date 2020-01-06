package com.golendukhin.accentureweatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeatherDao {
    @Insert
    fun insert(weather: Weather)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(weather: Weather)

    @Delete
    fun delete(weather: Weather)

    @Query("DELETE FROM weather_table")
    fun deleteAll()

    @Query("SELECT * FROM weather_table ORDER BY id DESC")
    fun getOrderedData(): LiveData<List<Weather>>

    @Query("SELECT * FROM weather_table WHERE id = :id")
    fun getItemById(id: Long): LiveData<Weather>

    @Query("SELECT * FROM weather_table ORDER BY id DESC LIMIT 1")
    suspend fun getLastItem(): Weather
}