package com.golendukhin.accentureweatherapp.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.golendukhin.accentureweatherapp.R
import com.golendukhin.accentureweatherapp.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        val application = requireNotNull(activity).application
        binding.lifecycleOwner = this

        val id = DetailFragmentArgs.fromBundle(arguments!!).id
        val detailViewModelFactory = DetailViewModelFactory(id, application)
        val detailViewModel = ViewModelProviders.of(this, detailViewModelFactory).get(DetailViewModel::class.java)
        binding.viewModel = detailViewModel

        return binding.root
    }
}