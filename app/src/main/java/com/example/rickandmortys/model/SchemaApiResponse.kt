package com.example.rickandmortys.model

import android.util.Log

data class SchemaApiResponse(
    val info: Info? = null,
    val results: List<CharacterAPI> = emptyList()
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class CharacterAPI(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
) {
    data class Origin(
        val name: String,
        val url: String
    )

    data class Location(
        val name: String,
        val url: String
    )

    // Método para convertir CharacterAPI a Character
    fun toCharacter(): Character {
        Log.i("SchemaApiResponse.toCharacter", this.toString())
        return Character(
            id = id,
            name = name,
            status = Character.Status.valueOf(status.uppercase()),
            species = species,
            type = type,
            gender = Character.Gender.valueOf(gender.uppercase()),
            image = image
        )
    }
}
