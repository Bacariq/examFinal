package com.example.myapplication.service

import com.example.myapplication.model.ApiMovie
import com.example.myapplication.model.ApiMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface HttpService {
    //************************************************************************************* get random
    //@Headers("Content-Type: application/json")
    @GET("search/movie")
    suspend fun getSearch(
        @Header("Authorization") authorizationHeader: String,
        @Query("query") query: String
    ) :  Response<ApiMovies>

    @GET("movie/{id}/similar?language=en-US&page=1")
    suspend fun getSame(
        @Header("Authorization") authorizationHeader: String,
        @Path("id") id: String
    ): Response<ApiMovies>

    @GET("trending/movie/day?language=en-US'")
    suspend fun getTrending(@Header("Authorization") authorizationHeader: String): Response<ApiMovies>
}
