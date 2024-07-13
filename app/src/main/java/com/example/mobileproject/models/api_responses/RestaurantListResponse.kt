package com.example.mobileproject.models.api_responses

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mobileproject.models.api_responses.utils.Address
import com.example.mobileproject.models.api_responses.utils.Agenda
import com.example.mobileproject.utils.Utils

class RestaurantListResponse (
    val restaurants: List<Restaurants>
){
    override fun toString(): String {
        var restaurantsString: String = ""
        for (restaurant in restaurants)
            restaurantsString += (restaurant.toString()+"\n")
        return restaurantsString
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertToPOJO(): List<com.example.mobileproject.models.Restaurant>{
        val restaurantsList: MutableList<com.example.mobileproject.models.Restaurant> = mutableListOf()
        for (r in this.restaurants)
            restaurantsList.add(r.convertToPOJO())
        return restaurantsList
    }

    class Restaurants(
        private val id: Int,
        private val name: String,
        private val image: String,
        private val address: String,
        private val openingTime: String,
        private val closingTime: String,
        private val menu: Menu
    ){
        override fun toString(): String {
            return "Restaurants (id=$id, name='$name', image='$image', address='$address', openingTime='$openingTime', closingTime='$closingTime', menu=$menu)"
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun convertToPOJO(): com.example.mobileproject.models.Restaurant {
            return com.example.mobileproject.models.Restaurant(
                this.id,
                this.name,
                this.image,
                Address(this.address, "", "", ""),
                Agenda(Utils.parseToLocalTime(this.openingTime), Utils.parseToLocalTime(this.closingTime)),
                this.menu.convertToPOJO()
            )
        }
    }

    class Menu(
        private val allergies: String
    ){
        override fun toString(): String {
            return "Menu (id='$allergies')"
        }

        fun convertToPOJO(): com.example.mobileproject.models.Menu{
            return com.example.mobileproject.models.Menu(mutableListOf(), this.allergies)
        }
    }
}