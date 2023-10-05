package com.example.myapplication.model

import com.google.gson.annotations.SerializedName


class ApiMovies (
    @SerializedName("drinks")
    val list: List<ApiMovie>
)

class ApiMovie (
    @SerializedName("strCategory")
    var name: String? = null
)