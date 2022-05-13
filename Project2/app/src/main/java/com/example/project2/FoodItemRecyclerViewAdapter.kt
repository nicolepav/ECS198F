package com.example.project2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.navArgument
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.R


class FoodItemRecyclerViewAdapter (private var food_items: List<FoodItem>)
    : RecyclerView.Adapter<FoodItemRecyclerViewAdapter.ViewHolder>(){

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.item_name)
        val descriptionTextView: TextView = itemView.findViewById(R.id.item_description)
        val priceTextView: TextView = itemView.findViewById(R.id.item_price)
        val taxIncludedTextView: TextView = itemView.findViewById(R.id.item_tax)
    }

    // called on initialize the list
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.food_item, parent, false)

        return ViewHolder(view)
    }

    // bind a view to its data on intialization or recycle
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // retrieve the data we want to bind
        val foodItem = food_items[position]

        holder.apply {
            nameTextView.text = foodItem.name
            nameTextView.text = foodItem.name
            descriptionTextView.text = foodItem.description
            priceTextView.text = "${foodItem.price}"
            taxIncludedTextView.text = when(foodItem.taxIncluded){
                true -> "(tax included)"
                false -> "(tax excluded)"
            }
        }
    }

    override fun getItemCount(): Int = food_items.size

    fun updateMenu(newMenu : List<FoodItem>?) {
        if (newMenu != null) {
            this.food_items = newMenu
        }

        Log.d("myMsg", this.food_items.toString())
        notifyDataSetChanged()
    }



}
