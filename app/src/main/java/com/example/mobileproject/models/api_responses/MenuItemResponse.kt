package com.example.mobileproject.models.api_responses

class MenuItemResponse(
    private val id: Int,
    private val name: String,
    private val price: Float,
    private val description: String,
    private val image: String,
    private val units: Int
){
    fun convertToPOJO(): com.example.mobileproject.models.MenuItem{
        return com.example.mobileproject.models.MenuItem(
            id = id,
            name = name,
            description = description,
            url = image,
            stock = units,
            price = price
        )
    }
    override fun toString(): String {
        return "MenuItemResponse(id=$id, name='$name', price=$price, description='$description', image='$image', units=$units)"
    }
}
