package com.example.mobileproject.models.api_responses

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.mobileproject.models.Menu
import com.example.mobileproject.models.MenuItem
import com.example.mobileproject.models.Order
import com.example.mobileproject.models.Restaurant
import com.example.mobileproject.models.User
import com.example.mobileproject.repository.RepositoryAsync
import com.example.mobileproject.utils.Utils
import com.example.semana1.enums.OrderStatus
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class OrderItemResponse (
    private var id: Int,
    private var status: String,
    private val restaurantId: Int,
    private val items: List<Int>,
    private val totalCost: Float,
    private val paidDate: String,
    private val deliveredDate: String
){
    init{
        if (paidDate != "")
            require(paidDate.matches(Regex("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")))
            {"El campo paidDate debe tener el formato 'yyyy-MM-ddTHH:mm:ss'"}
        if (deliveredDate != "")
            require(deliveredDate.matches(Regex("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")))
            {"El campo deliveredDate debe tener el formato 'yyyy-MM-ddTHH:mm:ss'"}
    }
    override fun toString(): String {
        return "OrderItemResponse(id=$id, status='$status', restaurantId=$restaurantId, items=$items, totalCost=$totalCost, paidDate='$paidDate', deliveredDate='${deliveredDate}')"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun convertToPOJO(user: User): Order{
        val restaurant: Restaurant = this.getRestaurants().filter { it.id == restaurantId }.get(0) //Teóricamente solo ha de devolver 1 elemento
        val menuTemp: Menu = this.getMenuRestaurant(restaurantId)
        restaurant.menu = menuTemp
        val order: Order = Order(
            id = this.id,
            user = user,
            status = OrderStatus.valueOf(this.status),
            restaurant = restaurant
        )

        if (paidDate != null) order.paidDate = Utils.parseToLocalDateTime(paidDate)
        if (deliveredDate != null) order.deliveredDate= Utils.parseToLocalDateTime(deliveredDate)

        //Añadimos los platos que se han pedido en la comanda
        val menuRestaurant: Menu = restaurant.menu
        for (itemId in this.items) {
            //order.add_menuItem(menuRestaurant.getItem(itemId))
            val item: MenuItem? = menuRestaurant.getItem(itemId)
            if (item != null) order.add_menuItem(item)
        }
        //Hemos añadido los platos del usuario dentro de la Order

        return order
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun getRestaurants(): List<Restaurant>{
        return RepositoryAsync.getRestaurants()
    }

    private suspend fun getMenuRestaurant(restaurantId: Int): Menu{
        val listItem: List<MenuItem> = RepositoryAsync.getMenuItemsByRestaurant(restaurantId)
        val menuTemp: Menu = Menu(mutableListOf(), "")
        for (item in listItem)
            menuTemp.addItem(item)
        return menuTemp
    }
}