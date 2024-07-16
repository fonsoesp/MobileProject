package com.example.mobileproject.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobileproject.R
import com.example.mobileproject.models.Restaurant
import com.example.mobileproject.models.User

class RestaurantAdapter(private val restaurants: List<Restaurant>, private val user: User) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.restaurant_name)
        val address: TextView = view.findViewById(R.id.restaurant_address)
        val image: ImageView = view.findViewById(R.id.restaurant_image)
        val openingTime: TextView = view.findViewById(R.id.restaurant_opening_time)
        val closingTime: TextView = view.findViewById(R.id.restaurant_closing_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("RestaurantAdapter", "onCreateViewHolder called")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            Log.d("RestaurantAdapter", "onBindViewHolder called for position $position")
            holder.name.text = restaurants[position].name
            holder.address.text = restaurants[position].address.toString()
            holder.openingTime.text = "Abre: ${ restaurants[position].agenda.getOpeningTime()}"
            holder.closingTime.text = "Cierra: ${ restaurants[position].agenda.getClosingTime()}"

            Glide.with(holder.itemView.context).load(restaurants[position].image).into(holder.image)

//            holder.itemView.setOnClickListener {
//                val context = holder.itemView.context
//                val intent = Intent(context, MenuActivity::class.java)
//
//                intent.putExtra("user", user)
//                intent.putExtra("restaurant", restaurants[position])
//                intent.putExtra("menu", restaurants[position].getMenu())
//                context.startActivity(intent)
//            }
        } catch (e: Exception) {
            Log.d("RestaurantAdapter", "Error in onBindViewHolder at position $position: ${e.message}")
        }
    }

    override fun getItemCount(): Int {
        Log.d("RestaurantAdapter", "getItemCount called, size: ${restaurants.size}")
        return restaurants.size
    }

}