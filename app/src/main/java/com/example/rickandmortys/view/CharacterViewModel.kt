package com.example.rickandmortys.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortys.model.Character
import com.example.rickandmortys.model.SchemaApiResponse
import com.example.rickandmortys.repository.ApiOperations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel : ViewModel() {
    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>>
        get() = _characters //GETTER

    init {
        ApiOperations.getCharacters(1).enqueue(object :
            Callback<SchemaApiResponse> {
            override fun onResponse(call: Call<SchemaApiResponse>, response: Response<SchemaApiResponse>) {
                if (response.isSuccessful) {
                    _characters.value = response.body()?.results?.map{ //Hacemos la conversión de tipos
                        it.toCharacter()
                    }?.take(10) //Tomando únicamente los 10 primeros elementos
                }
            }

            override fun onFailure(call: Call<SchemaApiResponse>, t: Throwable) {
                Log.e("CharacterViewModel", "Error fetching characters", t)
            }
        })
    }
}