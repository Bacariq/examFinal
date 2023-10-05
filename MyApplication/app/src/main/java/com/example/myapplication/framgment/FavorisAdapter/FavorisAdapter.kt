package com.example.myapplication.framgment.FavorisAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.DataMovie


class FavorisAdapter(
    private val ItemsListe: MutableList<DataMovie>,
    private val onLikeClickListener: OnLikeClickListener
) : RecyclerView.Adapter<FavoirsViewHolder>() {

    interface OnLikeClickListener {
        fun onLikeClick(item: DataMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoirsViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_movie_cell, parent, false)
        return FavoirsViewHolder(layout, onLikeClickListener)
    }

    override fun onBindViewHolder(holder: FavoirsViewHolder, position: Int) {
        val item: DataMovie = ItemsListe[position]
        holder.setupData(item)
    }

    override fun getItemCount(): Int {
        return ItemsListe.size
    }
}


class FavoirsViewHolder(
    private val view: View,
    private val onLikeClickListener: FavorisAdapter.OnLikeClickListener
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