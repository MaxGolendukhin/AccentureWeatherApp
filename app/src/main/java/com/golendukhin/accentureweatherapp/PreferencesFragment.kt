package com.golendukhin.accentureweatherapp

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.golendukhin.accentureweatherapp.databinding.FragmentPreferencesBinding


class PreferencesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPreferencesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_preferences, container, false)

        binding.lifecycleOwner = this

        val pref = context?.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
        val editor = pref?.edit()
        val city = pref?.getString(PREFERENCES_CITY_KEY, "Riga")
        binding.cityEditText.setText(city)

        binding.submitButton.setOnClickListener {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
            this.findNavController().navigate(PreferencesFragmentDirections.actionPreferencesFragmentToWeatherListFragment())
        }

        binding.cityEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                editor?.putString(PREFERENCES_CITY_KEY, s.toString())?.commit()
        }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
        return binding.root
    }
}