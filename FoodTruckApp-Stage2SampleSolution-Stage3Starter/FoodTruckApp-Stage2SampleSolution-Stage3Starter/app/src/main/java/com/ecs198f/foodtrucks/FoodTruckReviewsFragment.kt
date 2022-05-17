package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckMenuBinding
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckReviewsBinding

public const val ARG_OBJECT_Reviews = "object"

class FoodTruckReviewsFragment : Fragment() {

    private var ARG_OBJECT_Reviews: FoodTruck? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ARG_OBJECT_Reviews = it.getParcelable("ARG_OBJECT")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentFoodTruckReviewsBinding.inflate(inflater, container, false)
        val recyclerViewAdapter = FoodItemListRecyclerViewAdapter(listOf())

        binding.apply {
            reviewsRecyclerView.apply {
                adapter = recyclerViewAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        return binding.root
    }

    companion object {

        fun newInstance(truck: FoodTruck) =
            FoodTruckReviewsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("ARG_OBJECT_Reviews", truck)
                }
            }
    }
}