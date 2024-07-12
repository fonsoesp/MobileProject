package com.example.semana1.enums

enum class OrderStatus {
    IN_PROGRESS, //El pedido puede ser modificado en este estado por el usuario
    PAYED, //El pedido ya no puede ser modificado y ha sido pagado
    DELIVERED //El pedido ha sido entregado en su domicilio
}