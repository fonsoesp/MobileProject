package com.example.mobileproject.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/**
 * Vamos a utilizar esta clase estática para definir unas funciones que nos ayuden a
 * gestionar el programa, como parseo de datos, control de Exceptions, etc.
 */
object Utils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun parseToLocalDate(date: String, format: String = "dd/MM/yyyy"): LocalDate {
        //Función que toma un String en un formato y devuelve un objeto LocalDate
        val formatter = DateTimeFormatter.ofPattern(format)
        return try {
            LocalDate.parse(date, formatter)
        } catch (e: DateTimeParseException) {
            throw Exception("El formato de la fecha $date no coincide con $format: ${e.message}")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseToLocalTime(time: String, format: String = "HH:mm"): LocalTime {
        //Función que toma un String en un formato y devuelve un objeto LocalTime
        val formatter = DateTimeFormatter.ofPattern(format)
        return try {
            LocalTime.parse(time, formatter)
        } catch (e: DateTimeParseException) {
            throw Exception("El formato de la hora $time no coincide con $format: ${e.message}")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseLocalDateTimeToString (date: LocalDateTime?, format: String = "yyyy-MM-dd'T'HH:mm:ss"): String{
        if (date != null) {
            val formatter = DateTimeFormatter.ofPattern(format)
            return date.format(formatter)
        }
        return ""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseToLocalDateTime(date: String, format:String = "yyyy-MM-dd'T'HH:mm:ss"): LocalDateTime{
        try {
            val formatter = DateTimeFormatter.ofPattern(format)
            return LocalDateTime.parse(date, formatter)
        } catch (e: DateTimeParseException) {
            throw Exception ("Error al parsear la cadena: ${e.message}")
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun parseLocalTimeToString(time: LocalTime, format: String = "HH:mm"): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return time.format(formatter)
    }
}