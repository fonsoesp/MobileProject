package com.example.rickandmortys.repository

import com.example.rickandmortys.model.Character
import com.example.rickandmortys.network.CharacterDao

class CharacterRepository(private val characterDao: CharacterDao) {

    suspend fun insert(character: Character) {
        characterDao.insert(character)
    }

    suspend fun getAllCharacters(): List<Character> {
        return characterDao.getAllCharacters()
    }

    suspend fun clearDabaBase(){
        return characterDao.clearDB()
    }
}
