package com.example.myapplication.framgment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailMovieBinding
import com.example.myapplication.databinding.FragmentTrendingBinding
import com.example.myapplication.model.DataMovie


class DetailMovieFragment : Fragment() {

    private lateinit var binding : FragmentDetailMovieBinding
    private val args: DetailMovieFragmentArgs by navArgs()
    private lateinit var dataMovie : DataMovie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        dataMovie = args.myMovie!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SetupUi()
    }

    private fun SetupUi(){
        binding.DetailRate.text=dataMovie.vote_average
        binding.DetailResume.text=dataMovie.resume
        binding.DetailTitre.text=dataMovie.title

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original/"+dataMovie.poster_path)
            .into(binding.DetailImg1)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original/"+dataMovie.poster_path)
            .into(binding.DetailImg2)
    }
}