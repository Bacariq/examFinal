package com.example.myapplication.framgment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailMovieBinding
import com.example.myapplication.databinding.FragmentTrendingBinding


class DetailMovieFragment : Fragment() {

    private lateinit var binding : FragmentDetailMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)

        return binding.root
    }

}