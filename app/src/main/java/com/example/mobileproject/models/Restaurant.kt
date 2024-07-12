package com.example.mobileproject.models.api_responses

import com.example.mobileproject.models.api_responses.utils.Address
import com.example.mobileproject.models.api_responses.utils.Agenda
import com.example.mobileproject.models.Menu

/**
 * Debe representar el restaurante que aparece en la aplicación. De cada restaurante, nos interesa
 * conocer su nombre, su dirección, la hora de apertura y la hora de cierre y, finalmente, el
 * cojunto de productos que tiene a la venta representado con un menú (Menu). Para simplificar el
 * caso, se asumirá que un negocio abre y cierra todos los días a la misma hora.
 */
class Restaurant(
    val id: Int,
    val name: String,
    val address: Address,
    val agenda: Agenda,
    val menu: Menu
) {}