package com.example.mobileproject

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mobileproject.models.Menu
import com.example.mobileproject.models.MenuItem
import com.example.mobileproject.models.Restaurant
import com.example.mobileproject.repository.RepositoryAsync
import com.example.mobileproject.repository.RepositorySync
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i ("MainActivity", "Empezamos")

        lifecycleScope.launch{
            try{
                val restaurantes: List<Restaurant> = RepositoryAsync.getRestaurants()
                Log.i("RESTAURANTES", restaurantes.toString())
                println(restaurantes)
            } catch (e: Exception){
                Log.e ("MainActivity", "Error: ${e.message}")
            }
        }

        lifecycleScope.launch{
            try{
                val restaurantId = 1
                Log.i("MENU", "Antes de la llamada")
                val menu: List<MenuItem> = RepositoryAsync.getMenuItemsByRestaurant(restaurantId)
                Log.i("MENU", menu.toString())
                println(menu)
            } catch (e: Exception){
                Log.e ("MainActivity", "Error: ${e.message}")
            }
        }

    //        val restaurantes = RepositorySync.getRestaurants()
//        Log.i("MainActivity", restaurantes.toString())
//        println(restaurantes)
    }
}