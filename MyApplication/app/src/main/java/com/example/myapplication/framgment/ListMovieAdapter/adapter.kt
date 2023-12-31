package com.example.myapplication.framgment.ListMovieAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.*


class AdapterPage(
    private val ItemsListe: MutableList<DataMovie>,
    private val onLikeClickListener: OnLikeClickListener
) : RecyclerView.Adapter<ListeViewHolder>() {

    interface OnLikeClickListener {
        fun onLikeClick(item: DataMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListeViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_movie_cell, parent, false)
        return ListeViewHolder(layout, onLikeClickListener)
    }

    override fun onBindViewHolder(holder: ListeViewHolder, position: Int) {
        val item: DataMovie = ItemsListe[position]
        holder.setupData(item)
    }

    override fun getItemCount(): Int {
        return ItemsListe.size
    }
}


class ListeViewHolder(
    private val view: View,
    private val onLikeClickListener: AdapterPage.OnLikeClickListener
) : RecyclerView.ViewHolder(view) {

    val titre: TextView = view.findViewById(R.id.CellTitre)
    val date: TextView = view.findViewById(R.id.CellDate)
    val image: ImageView = view.findViewById(R.id.CellImage)
    val rate: TextView = view.findViewById(R.id.CellRate)

    fun setupData(item: DataMovie) {

        titre.text = item.title
        date.text = item.release_date
        Glide.with(view)
            .load("https://image.tmdb.org/t/p/original/"+item.poster_path)
            .into(image)
        rate.text = item.vote_average

        view.setOnClickListener {
            onLikeClickListener.onLikeClick(item)
        }
    }
}