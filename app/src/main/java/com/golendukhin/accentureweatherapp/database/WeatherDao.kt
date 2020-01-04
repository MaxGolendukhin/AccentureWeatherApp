package com.golendukhin.accentureweatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeatherDao {
    @Insert
    fun insert(weather: Weather)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(weather: Weather)

    @Delete
    fun delete(weather: Weather)

    @Query("DELETE FROM weather_table")
    fun deleteAll()

    @Query("SELECT * FROM weather_table ORDER BY id")
    fun getOrderedData(): LiveData<List<Weather>>

    @Query("SELECT * FROM weather_table WHERE id = :id")
    fun getItem(id: Long): LiveData<Weather>
}