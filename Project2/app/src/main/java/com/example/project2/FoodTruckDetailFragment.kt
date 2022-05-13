package com.example.project2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project2.R
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

class FoodTruckDetailFragment : Fragment() {

    private val foodTruckItems = mutableListOf<FoodItem>()
    private val args : FoodTruckDetailFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_truck_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val adapter = FoodItemRecyclerViewAdapter(foodTruckItems)
        val recyclerView = view.findViewById<RecyclerView>(R.id.detail_recyclerView)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        val service = (requireActivity() as MainActivity).foodService
        service.listFoodItems(args.truckid).enqueue(object : Callback<List<FoodItem>> {
            override fun onResponse(
                call: Call<List<FoodItem>>,
                response: Response<List<FoodItem>>
            ) {
                Log.d("myMsg", "api success")
                Log.d("myMsg", response.body().toString())
                adapter.updateMenu(response.body())
            }

            override fun onFailure(call: Call<List<FoodItem>>, t: Throwable) {
                Log.d("myMsg","api failure")
            }
        })

        service.foodTruck(args.foodTruck!!.id).enqueue(object : Callback<DetailInfo>{
            override fun onResponse(
                call: Call<DetailInfo>,
                response: Response<DetailInfo>
            ) {
                Log.d("myMsg", "api success")
                Log.d("myMsg", response.body().toString())
                val priceLV = response.body()!!.priceLeVel
                view.findViewById<TextView>(R.id.detail_priceLevel).text = when(priceLV){
                    2 -> "$$"
                    3 -> "$$$"
                    4 -> "$$$$"
                    5 -> "$$$$$"
                    else -> "$"
                }
            }

            override fun onFailure(
                call: Call<DetailInfo>,
                t: Throwable
            ) {
                Log.d("myMsg","api failure")
            }
        })

        fun parseOpenHours(openStr: String, closeStr: String) : String {
            // 2021-10-19T11:00:00
            val openDate = LocalDateTime.parse(openStr)
            val closeDate = LocalDateTime.parse(closeStr)
            var final = openDate.hour.toString() + " - " + closeDate.hour.toString()
            final += " " + openDate.dayOfWeek.toString() + " " + openDate.month.toString() + " " + openDate.dayOfMonth.toString()
            return final
        }

        view.findViewById<TextView>(R.id.detail_location).text = args.foodTruck.location
        view.findViewById<TextView>(R.id.detail_openTime).text = parseOpenHours(args.foodTruck.openTime, args.foodTruck.closeTime)
        Glide.with(view).load(args.foodTruck.imageUrl).into(view.findViewById<ImageView>(R.id.detail_image))
    }
}