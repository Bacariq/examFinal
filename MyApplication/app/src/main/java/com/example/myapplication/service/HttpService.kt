package com.example.myapplication.service

import com.example.myapplication.model.ApiMovie
import com.example.myapplication.model.ApiMovies
import retrofit2.Response
import retrofit2.http.GET

interface HttpServiceRandom {
    //************************************************************************************* get random
    //@Headers("Content-Type: application/json")
    @GET("random.php")
    suspend fun getSearch() :  Response<List<ApiMovies>>

    @GET("list.php?i=list")
    suspend fun getSame(): Response<List<ApiMovies>>

    @GET("list.php?c=list")
    suspend fun getById(): Response<ApiMovie>
}
