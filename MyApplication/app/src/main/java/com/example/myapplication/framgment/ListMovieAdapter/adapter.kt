package com.example.myapplication.framgment.ListMovieAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.DataMovieList


class AdapterPage(
    private val ItemsListe: MutableList<DataMovieList>,
    private val onLikeClickListener: OnLikeClickListener
) : RecyclerView.Adapter<SortieListeViewHolder>() {

    interface OnLikeClickListener {
        fun onLikeClick(item: DataMovieList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortieListeViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_movie_cell, parent, false)
        return SortieListeViewHolder(layout, onLikeClickListener)
    }

    override fun onBindViewHolder(holder: SortieListeViewHolder, position: Int) {
        val item: DataMovieList = ItemsListe[position]
        holder.setupData(item)
    }

    override fun getItemCount(): Int {
        return ItemsListe.size
    }
}


class SortieListeViewHolder(
    private val view: View,
    private val onLikeClickListener: AdapterPage.OnLikeClickListener
) : RecyclerView.ViewHolder(view) {

    //val Titre: TextView = view.findViewById(R.id.Page1CellText1)
    //val Date: TextView = view.findViewById(R.id.Page1CellText2)
    //val ImageSortie: ImageView = view.findViewById(R.id.Page1CellImage)
    //val BtnOpen: ImageButton = view.findViewById(R.id.Page1CellBtnOpent)

    fun setupData(item: DataMovieList) {
        //Titre.text  = item.name
        /*
                BtnOpen.setOnClickListener {
                    onLikeClickListener.onLikeClick("UPD", item)
                }
                */
    }
}