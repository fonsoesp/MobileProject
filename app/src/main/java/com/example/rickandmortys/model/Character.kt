package com.example.rickandmortys.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey val id: Int,
    val name: String,
    val status: Status,
    val species: String,
    val type: String,
    val gender: Gender,
    val image: String
) {
    init {
        // Validar que el ID sea mayor que 0
        require(id > 0) { "ID cannot be less than 1" }

        // Validar que el nombre no esté vacío y solo contenga letras, números y espacios
        require(name.isNotBlank() && name.matches(Regex("^[a-zA-Z0-9\\s]+$"))) {
            "Name can only contain letters, spaces, and numbers, and cannot be empty"
        }

        // Validar que species no esté vacío y solo contenga letras y espacios
        require(species.isNotBlank() && species.matches(Regex("^[a-zA-Z\\s]+$"))) {
            "Species can only contain letters and spaces, and cannot be empty"
        }

        // Validar que la URL de la imagen sea válida y termine con ".jpeg"
        require(image.matches(Regex("^https://rickandmortyapi\\.com/api/character/avatar/\\d+\\.jpeg\$"))) {
            "Image must follow the format: https://rickandmortyapi.com/api/character/avatar/{number}.jpeg"
        }
    }

    enum class Status {
        ALIVE, DEAD, UNKNOWN
    }

    enum class Gender {
        FEMALE, MALE, GENDERLESS, UNKNOWN
    }

}
