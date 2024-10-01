package com.universitatcarlemany.ex1.model

import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.ExpectedException
import org.junit.runners.MethodSorters
import org.junit.FixMethodOrder

import com.example.rickandmortys.model.Character

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CharacterTest {

    @get:Rule
    val exceptionRule: ExpectedException = ExpectedException.none()

    @Test
    fun `test1 valid character creation`() {
        val character = Character(
            id = 1,
            name = "Rick Sanchez",
            status = Character.Status.ALIVE,
            species = "Human",
            type = "",
            gender = Character.Gender.MALE,
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        )
        assertNotNull(character)
    }

    @Test
    fun `test2 invalid id`() {
        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("ID cannot be less than 1")
        Character(
            id = 0,
            name = "Rick Sanchez",
            status = Character.Status.ALIVE,
            species = "Human",
            type = "",
            gender = Character.Gender.MALE,
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        )
    }

    @Test
    fun `test3 invalid name`() {
        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("Name can only contain letters, spaces, and numbers, and cannot be empty")
        Character(
            id = 1,
            name = "Rick@Sanchez",
            status = Character.Status.ALIVE,
            species = "Human",
            type = "",
            gender = Character.Gender.MALE,
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        )
    }

    @Test
    fun `test4 invalid species`() {
        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("Species can only contain letters and spaces, and cannot be empty")
        Character(
            id = 1,
            name = "Rick Sanchez",
            status = Character.Status.ALIVE,
            species = "Human123",
            type = "",
            gender = Character.Gender.MALE,
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        )
    }

    @Test
    fun `test5 invalid image`() {
        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage("Image must follow the format: https://rickandmortyapi.com/api/character/avatar/{number}.jpeg")
        Character(
            id = 1,
            name = "Rick Sanchez",
            status = Character.Status.ALIVE,
            species = "Human",
            type = "",
            gender = Character.Gender.MALE,
            image = "https://invalidurl.com/image.jpeg"
        )
    }
}