package com.golendukhin.accentureweatherapp.list

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.golendukhin.accentureweatherapp.PREFERENCES_CITY_KEY
import com.golendukhin.accentureweatherapp.PREFERENCES_NAME
import com.golendukhin.accentureweatherapp.R
import com.golendukhin.accentureweatherapp.ResponseStatus
import com.golendukhin.accentureweatherapp.databinding.FragmentWeatherListBinding

class WeatherListFragment : Fragment() {
    private val weatherListViewModel: WeatherListViewModel by lazy {
        ViewModelProviders.of(this).get(WeatherListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentWeatherListBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_weather_list,
                container,
                false)
        binding.lifecycleOwner = this
        binding.weatherListViewModel = weatherListViewModel

        val adapter = WeatherListFragmentAdapter(WeatherItemClickListener {
            weatherListViewModel.displayDetails(it)
        })
        weatherListViewModel.navigateToDetails.observe(this, Observer {
            it?.let {
                this.findNavController()
                    .navigate(WeatherListFragmentDirections.actionListFragmentToDetailFragment(it))
                weatherListViewModel.displayDetailsComplete()
            }
        })
        weatherListViewModel.getData().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        binding.weatherList.adapter = adapter
        binding.weatherList.layoutManager = LinearLayoutManager(activity)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.save_menu_item -> {
                val pref = context?.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
                var responseStatus = weatherListViewModel.update(getString(R.string.API_key),
                    pref?.getString(PREFERENCES_CITY_KEY, "Riga"))
                when(responseStatus) {
                    ResponseStatus.HTTP_CONNECTION_ERROR ->
                        Toast.makeText(context,
                            "Problems with internet connection",
                            Toast.LENGTH_LONG).show()
                    ResponseStatus.ANOTHER_ERROR ->
                        Toast.makeText(context,
                            "Error with obtaining data",
                            Toast.LENGTH_LONG).show()
                }
            }
            R.id.preferences_menu_item -> this.findNavController().navigate(WeatherListFragmentDirections.actionListFragmentToPreferencesFragment())
        }
        return super.onOptionsItemSelected(item)
    }
}