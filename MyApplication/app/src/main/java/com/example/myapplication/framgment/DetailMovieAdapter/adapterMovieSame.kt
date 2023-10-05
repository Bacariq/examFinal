package com.example.myapplication.framgment.DetailMovieAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.*

class adapterMovieSame(
    private val ItemsListe: MutableList<DataMovie>
) : RecyclerView.Adapter<MovieSameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSameViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.detail_movie_cell, parent, false)
        return MovieSameViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MovieSameViewHolder, position: Int) {
        val item: DataMovie = ItemsListe[position]
        holder.setupData(item)
    }

    override fun getItemCount(): Int {
        return ItemsListe.size
    }
}


class MovieSameViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    val image: ImageView = view.findViewById(R.id.DetailCellImage)
    val titre: TextView = view.findViewById(R.id.DetailCellTitre)

    fun setupData(item: DataMovie) {

        Glide.with(view)
            .load("https://image.tmdb.org/t/p/original/"+item.poster_path)
            .into(image)
        titre.text = item.title

    }
}