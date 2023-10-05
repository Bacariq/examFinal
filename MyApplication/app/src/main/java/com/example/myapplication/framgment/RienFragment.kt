package com.example.myapplication.framgment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentListMovieBinding
import com.example.myapplication.databinding.FragmentRienBinding


class RienFragment : Fragment() {

    private lateinit var binding : FragmentRienBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRienBinding.inflate(inflater, container, false)

        return binding.root
    }

}