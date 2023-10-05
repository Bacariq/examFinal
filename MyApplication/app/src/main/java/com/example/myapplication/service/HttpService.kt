package com.example.myapplication.service

import com.example.myapplication.model.ApiMovie
import com.example.myapplication.model.ApiMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HttpService {
    //************************************************************************************* get random
    //@Headers("Content-Type: application/json")
    @GET("search/movie?query=dune&include_adult=false&language=en-US&page=1")
    suspend fun getSearch(
        @Header("Authorization") authorizationHeader: String,
        @Query("query") query: String
    ) :  Response<ApiMovies>

    @GET("list.php?i=list")
    suspend fun getSame(): Response<ApiMovies>

    @GET("list.php?c=list")
    suspend fun getById(): Response<ApiMovie>
}
