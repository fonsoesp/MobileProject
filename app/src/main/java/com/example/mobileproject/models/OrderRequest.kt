package com.example.mobileproject.models

import com.example.semana1.enums.OrderStatus

class OrderRequest (private val order: Order){
    private var status: String = ""
    private var restaurantId: Int = 0
    private var items: MutableList<Int> = mutableListOf()
    private var totalCost: Float = 0.0f
    private var paidDate: String = ""
    private var deliveredDate: String = ""

    init {
        this.status = order.status.name.uppercase()
        this.restaurantId = order.restaurant.id
        for (item in order.items) this.items.add(item.id)
        this.totalCost = this.order.price
        this.paidDate = order.paidDate //"" Si no está pagado o la fecha en formato correcto en caso de que lo esté
        this.deliveredDate = order.deliveredDate //"" Si no está enviado o la fecha en formato correcto en caso de que lo esté
    }

    fun toJson(): String {
        return "{" +
                "\"status\": \"${this.status}\"," +
                "\"restaurant\": ${this.restaurantId}," +
                "\"totalCost\": ${this.totalCost}," +
                "\"paidDate\": \"${this.paidDate}\"," +
                "\"deliveredDate\": \"${this.deliveredDate}\"," +
                "\"items\": ${this.getListOrdersId()}" +
                "}"
    }

    fun getListOrdersId(): MutableList<Int>{
        val retorno: MutableList<Int> = mutableListOf()
        for (order in this.order.items) retorno.add(order.id)
        return retorno
    }
}