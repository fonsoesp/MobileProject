package com.example.mobileproject.network

import com.example.mobileproject.models.api_responses.MenuItemResponse
import com.example.mobileproject.models.api_responses.OrderItemResponse
import com.example.mobileproject.models.api_responses.RestaurantListResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RestaurantAPI {
    @GET("restaurants")
    fun getRestaurants(): Call<RestaurantListResponse>

    @GET("restaurants/{id}/menu")
    fun getMenuByRestaurantId(@Path("id") idRestaurant: Int): Call<List<MenuItemResponse>>

    @GET("user/{email}/orders")
    fun getOrdersByUserEmail(@Path("email") email: String): Call <List<OrderItemResponse>>

    @POST("user/{email}/orders")
    fun createOrder(@Path("email") email: String, @Body order: OrderDto?): Call<OrderDto>

}