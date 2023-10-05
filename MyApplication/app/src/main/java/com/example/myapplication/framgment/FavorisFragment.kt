package com.example.myapplication.framgment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFavorisBinding
import com.example.myapplication.databinding.FragmentListMovieBinding
import com.example.myapplication.framgment.FavorisAdapter.FavorisAdapter
import com.example.myapplication.framgment.ListMovieAdapter.AdapterPage
import com.example.myapplication.model.DataMovie


class FavorisFragment : Fragment(), FavorisAdapter.OnLikeClickListener{

    private lateinit var binding : FragmentFavorisBinding
    private var favorisMovies : MutableList<DataMovie> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavorisBinding.inflate(inflater, container, false)

        return binding.root
    }


    private fun setupRecycler() {
        if(binding!=null){
            val recyclerView = binding!!.FavorisRecyclerView
            recyclerView?.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            recyclerView?.adapter = FavorisAdapter(favorisMovies, this)
        }
    }

    override fun onLikeClick(item: DataMovie) {
        TODO("Not yet implemented")
    }

}