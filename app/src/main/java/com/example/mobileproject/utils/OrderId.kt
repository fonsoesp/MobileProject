package com.example.mobileproject.utils

/**
 * Esta clase abstracta la utilizaremos para controlar el número de pedido de cada una
 * de las Orders
 */
object OrderId {
    private var numOrder = 0

    fun getNumOrder(): Int{
        return ++this.numOrder
    }
}