package com.golendukhin.accentureweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat

const val PREFERENCES_NAME = "accenture_weather_app_preferences"
const val PREFERENCES_CITY_KEY = "city"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

//
//
//        val s = SimpleDateFormat.getTimeInstance().format(1578293954000)
//        val d = SimpleDateFormat.getTimeInstance().format(1578319114000)







        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}