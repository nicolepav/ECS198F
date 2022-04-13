package com.example.project1

import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Before we can begin to display a list of food trucks, we must first create a data
 * class for a food truck. The food truck data class should contain the following attributes:
 * An ID (Int)
 * Name of the food truck (String)
 * Image of the Food Truck (Int)
 * Its location; ex. Silo, Quad, etc.  (String)
 * Time that it is open; ex. 11:00AM - 4:00PM (String)
 * Description of the food truck (String)
 * A link to the food truck’s website (String)
 *
 */

@Parcelize
data class FoodTruck (
    val ID : Int,
    val name : String,
    val image : Int,
    val location : String,
    val hours : String,
    val description : String,
    val link : String
) : Parcelable