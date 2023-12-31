package com.example.myapplication.extention

import com.example.myapplication.model.*

fun ApiMovie.ApiMovieToDataMovieList(): DataMovie {
    var dataMovieList: DataMovie = DataMovie()
    dataMovieList.id = this.id
    dataMovieList.title = this.title
    dataMovieList.release_date = this.release_date
    this.vote_average?.toDoubleOrNull()?.let {
        dataMovieList.vote_average = String.format("%.2f", it)
    }
    dataMovieList.resume = this.overview
    dataMovieList.poster_path = this.poster_path
    dataMovieList.backdrop_path = this.backdrop_path

    return dataMovieList
}