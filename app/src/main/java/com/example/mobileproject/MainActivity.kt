package com.example.mobileproject

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mobileproject.models.Menu
import com.example.mobileproject.models.MenuItem
import com.example.mobileproject.models.Order
import com.example.mobileproject.models.Restaurant
import com.example.mobileproject.models.api_responses.OrderItemResponse
import com.example.mobileproject.repository.RepositoryAsync
import com.example.mobileproject.repository.RepositorySync
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i ("MainActivity", "Empezamos")

        /*PROBAMOS EL ENDPOINT DE RESTAURANTES*/
        lifecycleScope.launch{
            try{
                val restaurantes: List<Restaurant> = RepositoryAsync.getRestaurants()
                Log.i("RESTAURANTES", restaurantes.toString())
                println(restaurantes)
            } catch (e: Exception){
                Log.e ("MainActivity", "Error: ${e.message}")
            }
        }

        /*PROBAMOS EL ENDPOINT DE TRAER EL MENÚ DEL RESTAURANTE #1*/
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


        /*PROBAMOS EL ENDPOINT DE TRAER LAS ORDERS DE UN USUARIO*/
        lifecycleScope.launch{
            try{
                val emailUser = "fonsoesp@gmail.coma"
                Log.i("ORDERS DE USUARIO", "Antes de la llamada")
                //Sería bueno tener un endpoint para no pasarle el email del usuario, sino el Objeto usuario
                val orders: List<Order> = RepositoryAsync.getOrdersByUser(emailUser)
                Log.i("ORDERS DE $emailUser", orders.toString())
                println(orders)
            } catch (e: Exception){
                Log.e ("MainActivity", "Error: ${e.message}")
            }
        }

        /*PROBAMOS EL ENDPOINT DE */

        /*PRUEBAS BORRAR*/
//        lifecycleScope.launch{
//            val variable: OrderItemResponse = OrderItemResponse(
//                id = 1,
//                items = mutableListOf(),
//                status =  "",
//                paidDate = "",
//                totalCost = 0.0f,
//                restaurantId = 3
//            )
//            val restaurantes: List<Restaurant> = variable.getRestaurants()
//            Log.i("MainACTIVITY", restaurantes.get(0).toString())
//        }
    //        val restaurantes = RepositorySync.getRestaurants()
//        Log.i("MainActivity", restaurantes.toString())
//        println(restaurantes)
    }
}