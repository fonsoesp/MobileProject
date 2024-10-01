package com.example.rickandmortys.adapter

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortys.R
import com.example.rickandmortys.model.Character
import com.example.rickandmortys.repository.CharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RickAndMortyCharacterAdapter(
    private val characters: List<Character>,
    private val repository: CharacterRepository
) : RecyclerView.Adapter<RickAndMortyCharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rickandmortycharacter, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount() = characters.size

    inner class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) { //Clase Interna. Si no, no reconoce repository de la clase superior
        private val characterImage: ImageView = view.findViewById(R.id.characterImage)
        private val characterTitle: TextView = view.findViewById(R.id.characterName)
        private val characterSubtitle: TextView = view.findViewById(R.id.characterSubtitle)
        //private val characterStatus: TextView = view.findViewById(R.id.characterStatus)
        private val actionButton: Button = view.findViewById(R.id.action_button)

        fun bind(character: Character) {
            characterTitle.text = String.format("%d. %s (%s)", character.id, character.name, character.gender.name.get(0))
            characterSubtitle.text = String.format ("%s, %s", character.status.name.uppercase(), character.species)
            Glide.with(itemView.context).load(character.image).into(characterImage)

            //Configuramos el Action del botón
            actionButton.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        repository.insert(character) // Intentar insertar el personaje
                        withContext(Dispatchers.Main) { //Notificamos al usuario la correcta inserción
                            Toast.makeText(
                                itemView.context,
                                "Personaje insertado correctamente",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: SQLiteConstraintException) {
                        // Manejar la excepción aquí
                        Log.e("CharacterAdapter", "Error en la inserción del personaje: ", e)

                        withContext(Dispatchers.Main) { //Notificamos al usuario el error
                            Toast.makeText(
                                itemView.context,
                                "Error en la inserción del personaje",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }
}