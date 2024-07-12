package com.example.mobileproject.repository

import android.util.Log
import com.example.mobileproject.models.api_responses.MenuItemResponse
import com.example.mobileproject.models.api_responses.OrderItemResponse
import com.example.mobileproject.models.api_responses.RestaurantListResponse
import com.example.mobileproject.network.RestaurantAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {
    private val api: RestaurantAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://carlemany.recursivity.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(RestaurantAPI::class.java)
    }

    /**
     * Definimos el método statico para llamar al endpoint de Restaurantes
     */
    fun getRestaurants() /*: MutableList<Restaurant>*/{
        val call = api.getRestaurants()
        call.enqueue(object: Callback<RestaurantListResponse>{
            override fun onResponse(
                call: Call<RestaurantListResponse>,
                response: Response<RestaurantListResponse>
            ) {
                if (response.isSuccessful) {
                    val datos: RestaurantListResponse? = response.body() // Aquí se meten los datos del Endpoint
                    if (datos != null) {
                        for (restaurant in datos.restaurants){
                            //Pasamos los datos de RestaurantListResponse a un List<Restaurant>
                        }
                    }
                } else {
                    Log.i("Repository", "Llamada incorrecta")
                    // StatusCode != 200..299
                }
            }

            override fun onFailure(call: Call<RestaurantListResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getMenuByRestaurant(restaurantId: Int){
        val call = api.getMenuByRestaurantId(restaurantId)
        call.enqueue(object: Callback<List<MenuItemResponse>>{

            override fun onResponse(
                call: Call<List<MenuItemResponse>>,
                response: Response<List<MenuItemResponse>>
            ) {
                val datos: List<MenuItemResponse>? = response.body() // Aquí se meten los datos del Endpoint
                if (datos != null) {
                    for (restaurant in datos){
                        //Pasamos los datos de MenuItemResponse a un List<Menu>
                    }
                }

            }

            override fun onFailure(call: Call<List<MenuItemResponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getOrdersByUser(email: String){
        val call = api.getOrdersByUserEmail(email)
        call.enqueue(object: Callback<List<OrderItemResponse>>{
            override fun onResponse(
                call: Call<List<OrderItemResponse>>,
                response: Response<List<OrderItemResponse>>
            ) {
                val datos: List<OrderItemResponse>? = response.body()
                if (datos != null){
                    for (orders in datos){
                        //Pasamos los datos de OrderItemResponse a un List<Order>
                    }
                }
            }

            override fun onFailure(call: Call<List<OrderItemResponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}