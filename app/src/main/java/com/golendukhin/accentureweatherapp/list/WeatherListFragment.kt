package com.golendukhin.accentureweatherapp.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.golendukhin.accentureweatherapp.R
import com.golendukhin.accentureweatherapp.database.WeatherDataBase
import com.golendukhin.accentureweatherapp.databinding.FragmentWeatherListBinding



class WeatherListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentWeatherListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_list, container, false)
        val application = requireNotNull(this.activity).application
        val weatherDao = WeatherDataBase.getInstance(application).weatherDao
        val weatherListFragmentFactoryFactory = WeatherListViewModelFactory(weatherDao, application)
        val weatherListModel = ViewModelProviders.of(this, weatherListFragmentFactoryFactory).get(WeatherListViewModel::class.java)

        binding.lifecycleOwner = this
        binding.weatherListViewModel = weatherListModel

        val adapter = WeatherListFragmentAdapter(WeatherItemClickListener { id ->

        })

        binding.weatherList.adapter = adapter
        binding.weatherList.layoutManager = LinearLayoutManager(activity)

        return binding.root
    }
}