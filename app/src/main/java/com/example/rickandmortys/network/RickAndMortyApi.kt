package com.example.rickandmortys.network

import com.example.rickandmortys.model.SchemaApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    fun getCharacters(@Query("page") page: Int): Call<SchemaApiResponse>
}