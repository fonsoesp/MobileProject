package com.example.mobileproject.models

import com.example.semana1.enums.Allergens
import java.text.DecimalFormat

/**
 * Reprsenta cada uno de los platos que ofrece el restaurante. De cada plato, nos interesa
 * conocer su nombre, el precio (con dos decimales), una descripción del plato, la URL de
 * la imagen del plato en formato de cadena de texto y, finalmente, el número de existencias
 * de ese plato, es decir, la cantidad de paltos que se pueden servir
 */
class MenuItem (
    val id: Int,
    val name: String,
    val description: String,
    val url: String,
    var stock: Int,
    val allergens: MutableList<Allergens>
){
    private var price: Float = 0.0f
        set(value){
            field = value
        }
        get() = DecimalFormat("#,##").format(field).toFloat() //CUIDADO CON LA COMA
    fun get_price(): Float{
        return this.price
    }

    override fun toString(): String {
        return "MenuItem(id=$id, name='$name', description='$description', url='$url', stock=$stock, allergens=$allergens, price=$price)"
    }

    constructor(
        id: Int,
        name: String,
        description: String,
        url: String,
        stock: Int,
        price: Float,
        allergens: MutableList<Allergens> = mutableListOf()
    ) : this(id, name, description, url, stock, allergens){
        this.price = price
    }


}