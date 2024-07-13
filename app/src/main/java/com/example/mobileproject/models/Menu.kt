package com.example.mobileproject.models

/**
 * Un menú es la carta que ofrece el restaurante, es decir, debe contener un listado
 * de todos los platos (MenuItem) que ofrece el restaurante. Además, el menú debe
 * contener información sobre posibles alergias en algunos de sus platos. Para más
 * simplicidad, se tratará esta información como una única cadena de texto
 */
class Menu (
    private val items: MutableList<MenuItem>,
    private val allergenInfo: String // LO LÓGICO ES QUE LA INFORMACIÓN DE ALÉRGENOS ESTÉ EN EL PRODUCTO, NO EN EL MENÚ
){
    fun addItem(item: MenuItem){
        this.items.add(item)
    }

    fun getItem(id: Int): MenuItem? {
        return items.find { it.id == id }
    }

    override fun toString(): String {
        return "Menu(items=$items, allergenInfo='$allergenInfo')"
    }


}