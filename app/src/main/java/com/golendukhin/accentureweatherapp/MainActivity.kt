package com.golendukhin.accentureweatherapp

import android.os.Bundle
import android.text.format.DateUtils
import androidx.appcompat.app.AppCompatActivity
import java.security.Timestamp
import java.text.SimpleDateFormat

import java.util.*
import java.util.Date.UTC
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.time.LocalDate
import java.time.LocalTime


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val time  = System.currentTimeMillis()

        val formatedDate = SimpleDateFormat.getDateInstance().format(time)
        val dd = formatedDate.toString()


        val time1  = System.currentTimeMillis()

        val formatedDate1 = SimpleDateFormat.getDateInstance().format(time)
        val dd1 = formatedDate.toString()

        val a = time == time1




//        val s = LocalDate.ofEpochDay(time)







//        val localDateTime = dateToLocalDateTime(date)
//        val startOfDay = localDateTime.with(LocalTime.MIN)
//
//
//
//
//
//
//        val stamp = Date(System.currentTimeMillis())
//        val date = stamp.time
//
//        DateUtils.
//
//        DateUtils.truncate(date, Calendar.DATE
//
//
//        val t = UTC(stamp.year, stamp.month, stamp.day, 0, 0, 0)
//
//        val ss = Date(t)
//        val dd = ss.time













        setContentView(R.layout.main_activity)
    }
}