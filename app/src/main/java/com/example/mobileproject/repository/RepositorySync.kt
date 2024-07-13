package com.example.mobileproject.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.mobileproject.models.Restaurant
import com.example.mobileproject.models.api_responses.RestaurantListResponse
import com.example.mobileproject.network.RestaurantAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepositorySync {
    private val api: RestaurantAPI
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://carlemany.recursivity.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RestaurantAPI::class.java)
    }

    /**
     * KOTLIN NO PERMITE LAS LLAMADAS SÍNCRONAS EN HILOS PRINCIPALES!!!
     * POR LO QUE NO SE PUEDE PROCEDER CON ESTE MODELO DE LLAMADAS
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun getRestaurants(): List<Restaurant>{
        val call = api.getRestaurants()
        val response = call.execute() //LLamada síncrona
        if (response.isSuccessful){
            val datos: RestaurantListResponse? = response.body()
            if (datos != null) {
                return datos.convertToPOJO()
            }
            else
                return emptyList()
        }else {
            // Manejo de errores
            Log.e("Repository", "Llamada incorrecta")
            throw RuntimeException("Llamada incorrecta")
        }
    }
}