package com.example.mobileproject.models.api_responses.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Esta clase la voy a utilizar para organizar la angenda de horarios (apertura y cierre)
 * de los Restaurantes. Podremos saber si un Restaurante está abierto o cerrado cualquier
 * día del año habiéndole configurado la lista de festivos cerrados, horarios, etc.
 */
class Agenda (
    private var openingTime: LocalTime,
    private var closingTime: LocalTime
){
    private lateinit var daysOff: MutableList<DayOfWeek> //días de descanso
    private lateinit var specialDaysClosed: MutableList<String> //En formato dd/MM
    private lateinit var specialDaysOpened: MutableList<String> //En formato dd/MM

    @RequiresApi(Build.VERSION_CODES.O)
    fun isOpen (date: LocalDateTime): Boolean{
        val day = date.format(DateTimeFormatter.ofPattern("dd/MM")) //String dd/MM
        val time = date.toLocalTime()
        val dayOfWeek = date.getDayOfWeek()

        //Si se consulta uno de los días especiales en los cuales están cerrados, FALSE
        if (day in this.specialDaysClosed) return false
        //Si se consulta un día de la semana y no corresponde con ningún día especial abierto FALSE
        if (dayOfWeek in this.daysOff && day !in this.specialDaysOpened) return false
        //Si el horario está fuera del horario de apertura FALSE
        if (time < this.openingTime || time > this.closingTime) return false

        return true
    }
}