package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckDetailBinding
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckMenuBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FoodTruckMenuFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFoodTruckMenuBinding.inflate(inflater, container, false)
        val recyclerViewAdapter = FoodItemListRecyclerViewAdapter(listOf())

        args.foodTruck.let {

            (requireActivity() as MainActivity).apply {
                title = it.name

                foodTruckService.listFoodItems(it.id).enqueue(object : Callback<List<FoodItem>> {
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
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FoodTruckMenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FoodTruckMenuFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}