package com.example.project2


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.time.LocalDateTime


class RecyclerViewAdapter (private var trucks: MutableList<FoodTruck>)
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
    ): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.food_truck_item, parent, false)

        return ViewHolder(view)
    }


    // bind a view to its data on initialization or recycle
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // retrieve the data we want to bind
        val truck = trucks[position]

        fun parseOpenHours(openStr: String, closeStr: String) : String {
            // 2021-10-19T11:00:00
            val openDate = LocalDateTime.parse(openStr)
            val closeDate = LocalDateTime.parse(closeStr)
            var final = openDate.hour.toString() + " - " + closeDate.hour.toString()
            final += " " + openDate.dayOfWeek.toString() + " " + openDate.month.toString() + " " + openDate.dayOfMonth.toString()
            return final
        }

        holder.nameTextView.text = truck.name
        holder.hoursTextView.text = parseOpenHours(truck.openTime, truck.closeTime)
        holder.locationTextView.text = truck.location
        Glide.with(holder.itemView).load(truck.imageUrl).into(holder.imgImageView)

        holder.itemView.setOnClickListener{
            val action = FoodTruckListFragmentDirections.actionFoodTruckListFragmentToFoodTruckDetailFragment(truck)
            action.truckid = truck.id
            action.truckname = truck.name
            action.trucklocation = truck.location
            action.truckhours = parseOpenHours(truck.openTime, truck.closeTime)
            action.truckImgUrl = truck.imageUrl
            action.foodTruck = truck
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = trucks.size

    fun updateTrucks(newTrucks: List<FoodTruck>?){

        if (newTrucks != null) {
            this.trucks = newTrucks.toMutableList()
        }

        Log.d("myMsg", this.trucks.toString())
        notifyDataSetChanged()
    }



}