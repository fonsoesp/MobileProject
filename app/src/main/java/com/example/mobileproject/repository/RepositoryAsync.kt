package com.example.mobileproject.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.mobileproject.models.Menu
import com.example.mobileproject.models.MenuItem
import com.example.mobileproject.models.Order
import com.example.mobileproject.models.Restaurant
import com.example.mobileproject.models.api_request.OrderRequest
import com.example.mobileproject.models.api_responses.MenuItemResponse
import com.example.mobileproject.models.api_responses.OrderItemResponse
import com.example.mobileproject.models.api_responses.RestaurantListResponse
import com.example.mobileproject.network.RestaurantAPI
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepositoryAsync {
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
//    fun getRestaurants() /*: MutableList<Restaurant>*/{
//        val call = api.getRestaurants()
//        call.enqueue(object: Callback<RestaurantListResponse>{
//            override fun onResponse(
//                call: Call<RestaurantListResponse>,
//                response: Response<RestaurantListResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val datos: RestaurantListResponse? = response.body() // Aquí se meten los datos del Endpoint
//                    if (datos != null) {
//                        //Pasamos los datos de RestaurantListResponse a un List<Restaurant>
//                    }
//                } else {
//                    Log.e("Repository", "Llamada incorrecta")
//                    // StatusCode != 200..299
//                }
//            }
//
//            override fun onFailure(call: Call<RestaurantListResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getRestaurants(): List<Restaurant> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getRestaurants().execute()
                if (response.isSuccessful) { //Response = 200..299
                    val datos: RestaurantListResponse? = response.body()
                    datos?.convertToPOJO() ?: emptyList() //Devuelve una List<Restaurant> o lista vacía
                } else {
                    Log.e("Repository", "Llamada incorrecta: ${response.code()}")
                    throw RuntimeException("Llamada incorrecta: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("Repository", "Error en la llamada: ${e.message}")
                throw e
            }
        }
    }


//    fun getMenuByRestaurant(restaurantId: Int){
//        val call = api.getMenuByRestaurantId(restaurantId)
//        call.enqueue(object: Callback<List<MenuItemResponse>>{
//
//            override fun onResponse(
//                call: Call<List<MenuItemResponse>>,
//                response: Response<List<MenuItemResponse>>
//            ) {
//                val datos: List<MenuItemResponse>? = response.body() // Aquí se meten los datos del Endpoint
//                if (datos != null) {
//                    for (restaurant in datos){
//                        //Pasamos los datos de MenuItemResponse a un List<Menu>
//                    }
//                }
//
//            }
//
//            override fun onFailure(call: Call<List<MenuItemResponse>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//    }


    suspend fun getMenuItemsByRestaurant(restaurantId: Int): List<MenuItem> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getMenuItemsByRestaurantId(restaurantId).execute()
                if (response.isSuccessful) { //Response = 200..299
                    val datos: List<MenuItemResponse>? = response.body()
                    //datos?.convertToPOJO() //Devuelve un Menu o null
                    val items: MutableList<MenuItem> = mutableListOf()
                    if (datos != null)
                        for (item in datos) items.add(item.convertToPOJO())
                    items.toList()
                } else {
                    Log.e("Repository", "Llamada incorrecta: ${response.code()}")
                    throw RuntimeException("Llamada incorrecta: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("Repository", "Error en la llamada: ${e.message}")
                throw e
            }
        }
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

    fun postOrderByUser(email: String, order: Order){
        val call = api.postCreateOder(email, OrderRequest(order))
        call.enqueue(object: Callback<OrderItemResponse>{
            override fun onResponse(
                call: Call<OrderItemResponse>,
                response: Response<OrderItemResponse>
            ) {
                val datos: OrderItemResponse? = response.body()
                if (datos != null){
                    //Pasamos los datos de OrderItemResponse a un Order o hacemos
                    //lo que queramos, porque ha salido bien la inserción
                }
                else{
                    val error: ResponseBody? = response.errorBody()
                    Log.e("API Call CreateOrder", error.toString())
                }

            }

            override fun onFailure(call: Call<OrderItemResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun putModifyOrder(email: String, orderId: Int, order: Order){
        val call = api.putModifyOrder(email, orderId, OrderRequest(order))
        call.enqueue(object: Callback<OrderItemResponse>{
            override fun onResponse(
                call: Call<OrderItemResponse>,
                response: Response<OrderItemResponse>
            ) {
                val datos: OrderItemResponse? = response.body()
                if (datos != null){
                    //Pasamos los datos de OrderItemResponse a un Order o hacemos
                    //lo que queramos, porque ha salido bien la inserción
                }
                else{
                    val error: ResponseBody? = response.errorBody()
                    Log.e("API Call CreateOrder", error.toString())
                }
            }

            override fun onFailure(call: Call<OrderItemResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}