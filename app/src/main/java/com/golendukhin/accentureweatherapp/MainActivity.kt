package com.golendukhin.accentureweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {



        val s = SimpleDateFormat.getTimeInstance().format(1578293954000)
        val d = SimpleDateFormat.getTimeInstance().format(1578319114000)







        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}