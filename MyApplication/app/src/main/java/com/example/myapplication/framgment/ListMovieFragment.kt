package com.example.myapplication.framgment

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.framgment.ListMovieAdapter.AdapterPage
import com.example.myapplication.model.DataMovieList

class ListMovieFragment() : Fragment(), AdapterPage.OnLikeClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_movie, container, false)
    }


    override fun onLikeClick(item: DataMovieList) {
        TODO("Not yet implemented")
    }

}