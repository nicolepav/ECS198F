package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckDetailBinding
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckMenuBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public const val ARG_OBJECT = "object"

class FoodTruckMenuFragment(): Fragment() {

    private var ARG_OBJECT: FoodTruck? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ARG_OBJECT = it.getParcelable("ARG_OBJECT")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFoodTruckMenuBinding.inflate(inflater, container, false)
        val recyclerViewAdapter = FoodItemListRecyclerViewAdapter(listOf())

        binding.apply {
            foodItemListRecyclerView.apply {
                    adapter = recyclerViewAdapter
                    layoutManager = LinearLayoutManager(context)
                }
        }

        (requireActivity() as MainActivity).apply {


            ARG_OBJECT?.id?.let {
                foodTruckService.listFoodItems(it).enqueue(object : Callback<List<FoodItem>> {
                    override fun onResponse(
                        call: Call<List<FoodItem>>,
                        response: Response<List<FoodItem>>
                    ) {
                        recyclerViewAdapter.updateItems(response.body()!!)
                    }

                    override fun onFailure(call: Call<List<FoodItem>>, t: Throwable) {
                        throw t
                    }
                })
            }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(truck: FoodTruck) = FoodTruckMenuFragment().apply {
            arguments = Bundle().apply {
                putParcelable("ARG_OBJECT", truck)
            }
        }
    }
}