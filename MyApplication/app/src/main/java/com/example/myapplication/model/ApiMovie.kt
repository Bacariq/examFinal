package com.example.myapplication.model

import com.google.gson.annotations.SerializedName


class ApiMovies (
    @SerializedName("page")
    val page: String? = null,
    @SerializedName("results")
    val list: List<ApiMovie>
)

class ApiMovie (
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("original_language")
    var original_language: String? = null,
    @SerializedName("original_title")
    var original_title: String? = null,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("popularity")
    var popularity: String? = null,
    @SerializedName("poster_path")
    var poster_path: String? = null,
    @SerializedName("release_date")
    var release_date: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("video")
    var video: String? = null,
    @SerializedName("vote_average")
    var vote_average: String? = null,
    @SerializedName("vote_count")
    var vote_count: String? = null
)
