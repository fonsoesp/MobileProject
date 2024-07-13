package com.example.mobileproject.models

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mobileproject.utils.OrderId
import com.example.mobileproject.utils.Utils
import com.example.semana1.enums.OrderStatus
import java.text.DecimalFormat
import java.time.LocalDateTime

/**
 * Representa un conjunto de platos (MenuItem) que ha pedido un usuario. Además, debe tener
 * conocimiento del usuario al que pertenece ese pedido, el coste total de ese pedido y el
 * estado de éste, que podrá ser: en curso, es decir el usuario aún puede añadir platos al
 * pedido y aún no ha pagado; pagado, es decir, el usuario ha tramitado el pedido y el
 * restaurante ya lo ha recibido; y finalmente, entregado, donde el usuario ya dispone de
 * su pedido en el domicilio (IN_PROGRESS, PAYED, DELIVERED)
 */
class Order (
    var id: Int = OrderId.getNumOrder(),
    var user: User,
    var status: OrderStatus,
    var restaurant: Restaurant
    ){

    var price: Float = 0.0f
        get() = DecimalFormat("#,##").format(field).toFloat() //CUIDADO CON LA COMA
    var items: MutableList<MenuItem> = mutableListOf()
    var paidDate: LocalDateTime? = null //"yyyy-MM-ddTHH:mm:ss"
    var deliveredDate: LocalDateTime? = null //"yyyy-MM-ddTHH:mm:ss"

    constructor(
        user: User,
        restaurant: Restaurant,
        command: MutableList<MenuItem>,
        status: OrderStatus = OrderStatus.IN_PROGRESS
    ) : this(user = user, status = status, restaurant = restaurant){ //Si inicializa la comanda por aquí, calcula precio. Si no, la comanda está vacía
        //Calculamos el precio de la comanda
        this.items = command
        for (item in this.items)
            this.price += item.get_price()
    }

    fun add_menuItem(menuItem: MenuItem){
        //Al añadir un item, recalculamos el precio de la comanda
        this.items.add(menuItem)
        this.price = this.recalculatePrice()
    }

    fun listCommand(){
        for (item in this.items) println (item)
    }

    private fun recalculatePrice(): Float{
        price = 0.0f
        for (item in this.items) price += item.get_price()
        return price
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun changeStatusToPayed (paidDate: LocalDateTime){
        Utils.parseLocalDateTimeToString(paidDate)
        this.status = OrderStatus.PAYED
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun changeStatusToDelivered (deliveredDate: LocalDateTime){
        Utils.parseLocalDateTimeToString(deliveredDate)
        this.status = OrderStatus.DELIVERED
    }

}