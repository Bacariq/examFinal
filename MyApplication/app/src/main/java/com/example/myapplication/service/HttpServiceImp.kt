package com.example.myapplication.service

import android.util.Log
import com.example.myapplication.model.ApiMovie
import com.example.myapplication.model.ApiMovies
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


public class HttpServiceImp {
    class LoggingInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val request: Request = chain.request()
                .newBuilder()
                .header("User-Agent", "PostmanRuntime/7.33.0")
                .build()

            val url = request.url.toString()
            Log.d("HTTP_REQUEST", "Request URL: $url")

            val response: okhttp3.Response = try {
                chain.proceed(request)
            } catch (e: IOException) {
                // Gérer les erreurs de requête ici si nécessaire
                throw e
            }

            val responseBody: ResponseBody? = response.body
            val bufferedResponseBody: ResponseBody = responseBody?.let { response.newBuilder().body(it).build().body }
                ?: return response

            val buffer = Buffer()
            bufferedResponseBody.source().readAll(buffer)
            val rawResponse = buffer.readUtf8()

            Log.d("RawResponse", rawResponse ?: "Response body is null")
            val newResponse = response.newBuilder()
                .body(ResponseBody.create(responseBody.contentType(), rawResponse))
                .build()

            return newResponse
        }
    }

    private fun retrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            callTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            addInterceptor(LoggingInterceptor())
        }.build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //************************************************************************************* Activitées
    suspend fun getSearch(query : String):  Response<ApiMovies> {
        return retrofit().create(HttpService::class.java).getSearch(
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1OGExOGU0ZDk5YjhhNzNmZWMyODg5NDc5YjdmMDAxNSIsInN1YiI6IjVjNGFkOWE3OTI1MTQxMDVjMTUzZWViYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.8339RuYRcAEJJ14GOqDxVdoTlxGpPXOX4r9zHYM05Wc",
            query
        )
    }

    suspend fun getSame(id: String):  Response<ApiMovies> {
        return retrofit().create(HttpService::class.java).getSame(id)
    }
    suspend fun getById():  Response<ApiMovie>{
        return retrofit().create(HttpService::class.java).getById()
    }

}
