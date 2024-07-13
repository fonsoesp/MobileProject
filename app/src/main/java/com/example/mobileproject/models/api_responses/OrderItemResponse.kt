package com.example.mobileproject.models.api_responses

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
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
    private val paidDate: String = "",
    private val deliveredDate: String = ""
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
        val comanda: Order = Order(
            id = this.id,
            user = user,
            status = OrderStatus.PAYED,
            restaurant = this.getRestaurants().filter { it.id == restaurantId }.get(0)
        )

        if (paidDate != "") comanda.paidDate = Utils.parseToLocalDateTime(paidDate)
        if (deliveredDate != "") comanda.deliveredDate= Utils.parseToLocalDateTime(deliveredDate)
        return comanda
    }


    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getRestaurants(): List<Restaurant>{
        return RepositoryAsync.getRestaurants()
    }
}