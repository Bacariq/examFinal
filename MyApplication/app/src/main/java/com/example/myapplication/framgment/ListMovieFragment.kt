package com.example.myapplication.framgment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentListMovieBinding
import com.example.myapplication.extention.ApiMovieToDataMovieList
import com.example.myapplication.framgment.ListMovieAdapter.AdapterPage
import com.example.myapplication.model.*
import com.example.myapplication.service.HttpServiceImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Response

class ListMovieFragment() : Fragment(), AdapterPage.OnLikeClickListener {

    private lateinit var binding : FragmentListMovieBinding
    private val httpService by lazy { HttpServiceImp() }
    private var movies : MutableList<DataMovie> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListMovieBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtonListeners()
        retrieveData("Dune")
    }

    override fun onLikeClick(item: DataMovie) {
        val action = ListMovieFragmentDirections.actionListMovieFragmentToDetailMovieFragment(item)
        findNavController().navigate(action)
    }


    //*********************************************************************************** Http

    private fun retrieveData(searchTxt : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = httpService.getSearch(searchTxt)
            withContext(Dispatchers.Main) {
                response.body()?.let { body ->
                    updateDatas(Response = body)
                }
            }
        }
    }

    private fun updateDatas(Response: ApiMovies) {
        movies.clear()
        Response.list.forEach{it ->
            movies.add(it.ApiMovieToDataMovieList())
        }
        setupRecycler()
    }

    private fun setupRecycler() {
        if(binding!=null){
            val recyclerView = binding!!.MovieListRecycler
            recyclerView?.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            recyclerView?.adapter = AdapterPage(movies, this)
        }
    }

    //*********************************************************************************** Listenner

    private fun setupButtonListeners() {
        val actionBarBtnOk = requireActivity().findViewById<ImageButton>(R.id.ActionBarBtnOk)
        actionBarBtnOk?.setOnClickListener {
            Log.d("LM", "setupButtonListeners")
            val customToolbarTextSearch = requireActivity().findViewById<EditText>(R.id.ActionTxtSearch)
            retrieveData(customToolbarTextSearch.text.toString())
        }
    }

}