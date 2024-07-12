package com.example.mobileproject.models.api_responses

class RestaurantListResponse (
    val restaurants: List<Restaurants>
){
    override fun toString(): String {
        var restaurantsString: String = ""
        for (restaurant in restaurants)
            restaurantsString += (restaurant.toString()+"\n")
        return restaurantsString
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
            return "Restaurant (id=$id, name='$name', image='$image', address='$address', openingTime='$openingTime', closingTime='$closingTime', menu=$menu)"
        }
    }

    class Menu(
        private val allergies: String
    ){
        override fun toString(): String {
            return "Menu (id='$allergies')"
        }
    }
}