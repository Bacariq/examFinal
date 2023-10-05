package com.example.myapplication.framgment.TrendingAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.DataMovie


class AdapterTrending(
    private val ItemsListe: MutableList<DataMovie>
) : RecyclerView.Adapter<TrendingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.trending_cell, parent, false)
        return TrendingViewHolder(layout)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val item: DataMovie = ItemsListe[position]
        holder.setupData(item)
    }

    override fun getItemCount(): Int {
        return ItemsListe.size
    }
}


class TrendingViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    val image: ImageView = view.findViewById(R.id.TrendingImg)
    val rate: TextView = view.findViewById(R.id.TrendingRate)

    fun setupData(item: DataMovie) {

        Glide.with(view)
            .load("https://image.tmdb.org/t/p/original/"+item.poster_path)
            .into(image)
        rate.text = item.vote_average

    }
}