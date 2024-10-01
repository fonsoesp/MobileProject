package com.example.rickandmortys.network

import androidx.room.Dao
import androidx.room.DeleteTable
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.rickandmortys.model.Character

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(character: Character)

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<Character>

    @Query("DELETE FROM characters")
    suspend fun clearDB()
}