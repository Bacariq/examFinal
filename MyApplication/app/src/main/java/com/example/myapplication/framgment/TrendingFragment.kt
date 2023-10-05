package com.example.myapplication.framgment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentTrendingBinding
import com.example.myapplication.extention.ApiMovieToDataMovieList
import com.example.myapplication.framgment.DetailMovieAdapter.adapterMovieSame
import com.example.myapplication.framgment.TrendingAdapter.AdapterTrending
import com.example.myapplication.model.ApiMovies
import com.example.myapplication.model.DataMovie
import com.example.myapplication.service.HttpServiceImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrendingFragment : Fragment() {

    private lateinit var binding : FragmentTrendingBinding
    private var trendingMovies : MutableList<DataMovie> = mutableListOf()
    private val httpService by lazy { HttpServiceImp() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrieveData()
    }

//*********************************************************************************** Http

    private fun retrieveData() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = httpService.getTrending()
            withContext(Dispatchers.Main) {
                response.body()?.let { body ->
                    updateDatas(Response = body)
                }
            }
        }
    }

    private fun updateDatas(Response: ApiMovies) {
        trendingMovies.clear()
        Response.list.forEach{it ->
            trendingMovies.add(it.ApiMovieToDataMovieList())
        }
        setupRecycler()
    }

    private fun setupRecycler() {
        if(binding!=null){
            val recyclerView = binding!!.trendingRecyclerView
            recyclerView?.layoutManager = GridLayoutManager(requireContext(), 3)
            recyclerView?.adapter = AdapterTrending(trendingMovies)
        }
    }
}