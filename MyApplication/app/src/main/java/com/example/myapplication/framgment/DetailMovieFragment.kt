package com.example.myapplication.framgment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailMovieBinding
import com.example.myapplication.databinding.FragmentTrendingBinding
import com.example.myapplication.extention.ApiMovieToDataMovieList
import com.example.myapplication.framgment.DetailMovieAdapter.adapterMovieSame
import com.example.myapplication.framgment.ListMovieAdapter.AdapterPage
import com.example.myapplication.model.ApiMovies
import com.example.myapplication.model.DataMovie
import com.example.myapplication.service.HttpServiceImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailMovieFragment : Fragment() {

    private lateinit var binding : FragmentDetailMovieBinding
    private val args: DetailMovieFragmentArgs by navArgs()
    private lateinit var dataMovie : DataMovie
    private val httpService by lazy { HttpServiceImp() }
    private var sameMovies : MutableList<DataMovie> = mutableListOf()

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
        retrieveData(dataMovie.id!!)
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


    //*********************************************************************************** Http

    private fun retrieveData(movieId : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = httpService.getSame(movieId)
            withContext(Dispatchers.Main) {
                response.body()?.let { body ->
                    Log.d("LM", "retrieveData")
                    updateDatas(Response = body)
                }
            }
        }
    }

    private fun updateDatas(Response: ApiMovies) {
        sameMovies.clear()
        Response.list.forEach{it ->
            sameMovies.add(it.ApiMovieToDataMovieList())
        }
        setupRecycler()
    }

    private fun setupRecycler() {
        if(binding!=null){
            val recyclerView = binding!!.recyclerView
            recyclerView?.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            recyclerView?.adapter = adapterMovieSame(sameMovies)
        }
    }
}