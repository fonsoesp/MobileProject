package com.example.rickandmortys

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortys.adapter.RickAndMortyCharacterAdapter
import com.example.rickandmortys.persistence.CharacterDatabase
import com.example.rickandmortys.repository.CharacterRepository
import com.example.rickandmortys.view.CharacterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: CharacterViewModel by viewModels()
    private lateinit var repository: CharacterRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar la base de datos y el repositorio
        val database = CharacterDatabase.getDatabase(this)
        val characterDao = database.characterDao()
        repository = CharacterRepository(characterDao)

        //Pintamos los registros del RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.characters.observe(this) { characters ->
            recyclerView.adapter = RickAndMortyCharacterAdapter(characters, repository)
        }

        //Asignamos la acción de borrar los personajes de la BD (DELETE FROM characters)
        val clearDatabaseButton = findViewById<Button>(R.id.clearDB)
        clearDatabaseButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    repository.clearDabaBase() // Método para borrar los datos de la tabla
                    Log.d("MainActivity", "BASE DE DATOS VACÍA")
                } catch (e: Exception) {
                    Log.e("MainActivity", "ERROR A LA HORA DE BORRAR LA BASE DE DATOS", e)
                }
            }
        }
    }
}
