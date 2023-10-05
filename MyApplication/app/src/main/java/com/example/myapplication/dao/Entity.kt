package com.example.myapplication.dao

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Favoris(
    @PrimaryKey(autoGenerate = true)
    var pkFavoris: Long = 0,
    var id: String? = null,
    var title: String? = null,
    var release_date: String? = null,
    var vote_average: String? = null,
    var resume: String? = null,
    var backdrop_path: String? = null,
    var poster_path: String? = null)