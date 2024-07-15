package com.example.mobileproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileproject.models.Menu
import com.example.mobileproject.models.MenuItem
import com.example.mobileproject.models.Order
import com.example.mobileproject.models.Restaurant
import com.example.mobileproject.models.User
import com.example.mobileproject.models.api_responses.OrderItemResponse
import com.example.mobileproject.models.api_responses.utils.Address
import com.example.mobileproject.repository.RepositoryAsync
import com.example.mobileproject.repository.RepositorySync
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private val viewModel: RestaurantViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i ("MainActivity", "Empezamos")

        val email = "carles94bcn@gmail.com"
        val user = User(
            firstName = "Carles",
            lastName = "Gallel",
            birthday = LocalDate.of(2000, 1, 1),
            phone = "+376 878 300",
            address = Address("Calle", "", "", ""),
            email = email
        )
        //val user = User("Carles", "Gallel", LocalDate.of(2000, 1, 1), "Calle", "+376 878 300", email, "https://t4.ftcdn.net/jpg/05/89/93/27/360_F_589932782_vQAEAZhHnq1QCGu5ikwrYaQD0Mmurm0N.png")

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
        toolbarTitle.text = "RESTAURANTES"

        val userIcon: ImageView = findViewById(R.id.user_icon)
        userIcon.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra("user", user)

            startActivity(intent)
        }

        val cartIcon: ImageView = findViewById(R.id.cart_icon)
        cartIcon.setOnClickListener {
            val intent = Intent(this, OrderSummaryActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }


//        /*PROBAMOS EL ENDPOINT DE RESTAURANTES*/
//        lifecycleScope.launch{
//            try{
//                val restaurantes: List<Restaurant> = RepositoryAsync.getRestaurants()
//                Log.i("RESTAURANTES", restaurantes.toString())
//                println(restaurantes)
//            } catch (e: Exception){
//                Log.e ("MainActivity", "Error: ${e.message}")
//            }
//        }
//
//        /*PROBAMOS EL ENDPOINT DE TRAER EL MENÚ DEL RESTAURANTE #1*/
//        lifecycleScope.launch{
//            try{
//                val restaurantId = 1
//                Log.i("MENU", "Antes de la llamada")
//                val menu: List<MenuItem> = RepositoryAsync.getMenuItemsByRestaurant(restaurantId)
//                Log.i("MENU", menu.toString())
//                println(menu)
//            } catch (e: Exception){
//                Log.e ("MainActivity", "Error: ${e.message}")
//            }
//        }
//
//
//        /*PROBAMOS EL ENDPOINT DE TRAER LAS ORDERS DE UN USUARIO*/
//        lifecycleScope.launch{
//            try{
//                val emailUser = "fonsoesp@gmail.com"
//                Log.i("ORDERS DE USUARIO", "Antes de la llamada")
//                //Sería bueno tener un endpoint para no pasarle el email del usuario, sino el Objeto usuario
//                val orders: List<Order> = RepositoryAsync.getOrdersByUser(emailUser)
//                Log.i("ORDERS DE $emailUser", orders.toString())
//                println(orders)
//            } catch (e: Exception){
//                Log.e ("MainActivity", "Error: ${e.message}")
//            }
//        }
//
//        /*PROBAMOS EL ENDPOINT DE TRAER LAS ORDERS DE UN USUARIO ERRÓNEO*/
//        lifecycleScope.launch{
//            try{
//                val emailUser = "XXXXXXXXXX@gmail.com"
//                Log.i("ORDERS DE USUARIO", "Antes de la llamada")
//                //Sería bueno tener un endpoint para no pasarle el email del usuario, sino el Objeto usuario
//                val orders: List<Order> = RepositoryAsync.getOrdersByUser(emailUser)
//                Log.i("ORDERS DE $emailUser", orders.toString())
//                println(orders)
//            } catch (e: Exception){
//                Log.e ("MainActivity", "Error: ${e.message}")
//            }
//        }


    }
}