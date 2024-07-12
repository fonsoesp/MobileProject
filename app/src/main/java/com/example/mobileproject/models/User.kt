package com.example.mobileproject.models

import com.example.mobileproject.models.api_responses.utils.Address
import com.example.semana1.enums.OrderStatus
import java.time.LocalDate

/**
 * Debe representar un cliente que quiere hacer un pedido a uno de los restaurantes que tendrá
 * la aplicación. De cada cliente, nos interesa conocer su nombre, sus apellidos, su fecha de
 * nacimiento, su dirección, un número de teléfono de contacto que seguirá el formato de teléfonos
 * andorrano, un email y el conjunto de pedidos (Orders) que ha realizado en la aplicación. Además
 * es importante poder diferenciar entre aquellos pedidos que están en curso, los que ya han sido
 * pagados y están pendientes de entrega y los que ya se han entregado. Todo esto hay que diseñarlo
 * de la manera más eficiente posible, es decir, la que permite consultar todos los pedidos en
 * función de su estado de la manera más eficiente posible.
 */
class User(
    val firstName: String,
    val lastName: String,
    val birthday: LocalDate,
    var address: Address,
    val orders: MutableList<Order> = mutableListOf()
){
    var phone: String = "" //Formato Andorrano (+37 000 00 00 00)
        /*Si llamas a Andorra desde España (o cualquier otro país), debes marcar el 00 376 (o +376),
         que es el prefijo del país. Seguidamente, podrás marcar el número al que quieras llamar,
         éste tendrá 6 dígitos y empezará por “8” si es fijo, por “3” si es móvil de contrato, o “6”
         si es móvil de tarjeta.*/
        set(value) {
            val pattern = "^00 376 [8]\\d{5}|\\+376 [8]\\d{5}|00 376 [36]\\d{5}|\\+376 [36]\\d{5}\$"
            require(Regex(pattern).matches(value)) {
                "El número de teléfono ${value} no corresponde con un número de teléfono Andorrano"
            }
            field = value
        }

    var email: String = "" //Formato EMAIL
        set(value){
            val pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"
            require(Regex(pattern).matches(value)) {
                "El email introducido ${value} no es válido"
            }
            field = value
        }

    constructor(
        firstName: String,
        lastName: String,
        birthday: LocalDate,
        phone: String,
        address: Address,
        email: String,
        orders: MutableList<Order> = mutableListOf()
    ) : this(firstName, lastName, birthday, address, orders){
        this.phone = phone //Aquí pasa por el setter y su correspondiente pattern
        this.email = email
    }

    fun getOrdersByStatus(status: OrderStatus):List<Order>{
        return orders.filter{ it.status == status }
    }

    fun addOrder(order: Order){
        orders.add(order)
    }
}