package com.example.mobileproject.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.universitatcarlemany.activity2.model.Menu
import com.universitatcarlemany.activity2.model.MenuItem
import com.universitatcarlemany.activity2.model.MenuItemResponse
import com.universitatcarlemany.activity2.model.OrderDto
import com.universitatcarlemany.activity2.model.Restaurant
import com.universitatcarlemany.activity2.model.RestaurantListResponse
import com.universitatcarlemany.activity2.model.User
import com.universitatcarlemany.activity2.repository.RestaurantRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class RestaurantViewModel() : ViewModel() {
    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> get() = _restaurants

    private val _restaurantsLoaded = MutableLiveData<Boolean>()
    val restaurantsLoaded: LiveData<Boolean> get() = _restaurantsLoaded

    private fun updateRestaurantsLoaded(loaded: Boolean) {
        _restaurantsLoaded.postValue(loaded)
    }

    fun loadRestaurantsAndOrders(user: User, onSuccess: (List<OrderDto>) -> Unit, onFailure: (Throwable) -> Unit) {
        loadRestaurants()
        _restaurants.observeForever { restaurants ->
            if (restaurants.isNotEmpty()) {
                RestaurantRepository.getOrders(user, onSuccess, onFailure)
            }
        }
    }

    private fun loadRestaurants(idRestaurant: Int? = null) {
        RestaurantRepository.getRestaurants().enqueue(object :
            Callback<RestaurantListResponse> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<RestaurantListResponse>, response: Response<RestaurantListResponse>) {
                if (response.isSuccessful) {
                    val allRestaurants = response.body()?.restaurants?.map { restaurant ->
                        val formatter = DateTimeFormatter.ofPattern("HH:mm")
                        val newRestaurant = Restaurant(
                            restaurant.id,
                            restaurant.name,
                            restaurant.image,
                            restaurant.address,
                            LocalTime.parse(restaurant.openingTime, formatter),
                            LocalTime.parse(restaurant.closingTime, formatter),
                        )
                        newRestaurant.setMenu(Menu(restaurant.menu.allergies))
                        newRestaurant
                    }

                    allRestaurants?.forEach { restaurant ->
                        RestaurantRepository.getMenuByRestaurantId(restaurant.getId()).enqueue(object :
                            Callback<List<MenuItemResponse>> {
                            override fun onResponse(call: Call<List<MenuItemResponse>>, response: Response<List<MenuItemResponse>>) {
                                if (response.isSuccessful) {
                                    val menuItems = response.body()?.map { menuItemResponse ->
                                        MenuItem(
                                            menuItemResponse.id,
                                            menuItemResponse.name,
                                            menuItemResponse.price.toDouble(),
                                            menuItemResponse.description,
                                            menuItemResponse.image,
                                            menuItemResponse.units,
                                            restaurant
                                        )
                                    } ?: listOf()
                                    restaurant.getMenu().addAllItems(menuItems)
                                } else {
                                    Log.e("RestaurantViewModel", "Error fetching menu items: ${response.errorBody()?.string()}")
                                }

                                updateRestaurantsLoaded(true)
                            }

                            override fun onFailure(call: Call<List<MenuItemResponse>>, t: Throwable) {
                                Log.e("RestaurantViewModel", "Error fetching menu items", t)
                            }
                        })
                    }

                    _restaurants.value = if (idRestaurant != null) {
                        allRestaurants?.filter { it.getId() == idRestaurant }
                    } else {
                        allRestaurants
                    }
                }
            }

            override fun onFailure(call: Call<RestaurantListResponse>, t: Throwable) {
                Log.e("RestaurantViewModel", "Error fetching restaurants", t)
            }
        })

        // for every restaurant, call the getMenuByRestaurantId method

    }

}
