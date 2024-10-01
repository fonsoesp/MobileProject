package com.example.rickandmortys.repository

import com.example.rickandmortys.network.RickAndMortyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiOperations {
    private val api: RickAndMortyApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RickAndMortyApi::class.java)
    }

    fun getCharacters(page: Int = 1) = api.getCharacters(page)
}