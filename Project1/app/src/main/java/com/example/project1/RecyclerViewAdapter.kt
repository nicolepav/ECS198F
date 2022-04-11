package com.example.project1

import android.service.autofill.TextValueSanitizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

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

    }

    override fun getItemCount(): Int = trucks.size

}