package com.example.metgallery.data.remote

import com.example.metgallery.data.remote.dto.ApiElements
import com.example.metgallery.data.remote.dto.ApiElemDetails
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .baseUrl("https://collectionapi.metmuseum.org/public/collection/v1/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface METApi{
    @GET("search")
    suspend fun getElements(@Query("q") query: String): Response<ApiElements>

    @GET("objects/{objectID}")
    suspend fun getElemDetails(@Path("objectID") objectId: Int): Response<ApiElemDetails>
}

val metApi = retrofit.create(METApi::class.java)
