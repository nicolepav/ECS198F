package com.example.project2

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FoodTruckListFragment : Fragment() {

    private val foodTruckData = mutableListOf<FoodTruck>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_truck_list, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState:
        Bundle?
    ){
        super.onViewCreated(view, savedInstanceState)
        val adapter = RecyclerViewAdapter(foodTruckData)
        val recyclerView = view.findViewById<RecyclerView>(R.id.RecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)



        val service = (requireActivity() as MainActivity).foodService
        service.listFoodTrucks().enqueue(object : Callback<List<FoodTruck>> {
            override fun onResponse(
                call: Call<List<FoodTruck>>,
                response: Response<List<FoodTruck>>
            ) {
                Log.d("myMsg", "api success")
                adapter.updateTrucks(response.body())
            }

            override fun onFailure(call: Call<List<FoodTruck>>, t: Throwable) {
                Log.d("myMsg","api failure")
            }
        })
    }
}