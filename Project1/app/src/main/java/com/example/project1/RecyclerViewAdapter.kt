package com.example.project1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.databinding.FoodTruckItemBinding

class RecyclerViewAdapter (private var trucks: List<FoodTruck>)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.food_truck_item_name)
        val locationTextView: TextView = itemView.findViewById(R.id.food_truck_item_location)
        val hoursTextView: TextView = itemView.findViewById(R.id.food_truck_item_hours)
        val imgImageView: ImageView = itemView.findViewById(R.id.food_truck_item_img)

    }


    // called on initialize the list
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.food_truck_item, parent, false)



        return ViewHolder(view)
    }


    // bind a view to its data on intialization or recycle
    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        // retrieve the data we want to bind
        val truck = trucks[position]

        holder.nameTextView.text = truck.name
        holder.hoursTextView.text = truck.hours
        holder.locationTextView.text = truck.location
        holder.imgImageView.setImageResource(truck.image)       // not sure if this one is exactly right


        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, FoodTruckDetail::class.java)

            // how do I get the correct foodtruck onto my intent
            val intentTruck : FoodTruck =
                FoodTruck(truck.ID, truck.name, truck.image,truck.location,truck.hours,truck.description,truck.link)


            intent.putExtra("truck", intentTruck)
            startActivity(it.context, intent, null)
        }
    }

    override fun getItemCount(): Int = trucks.size

}