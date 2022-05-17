package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class FoodTruckDetailFragment : Fragment() {
    private val args: FoodTruckDetailFragmentArgs by navArgs()

    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, view: View,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFoodTruckDetailBinding.inflate(inflater, container, false)
        val viewPager = view.findViewById<ViewPager2>(R.id.detailViewPager)
        val tabLayout = view.findViewById<TabLayout>(R.id.detailTabLayout)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            viewPager.setCurrentItem(0, true)
            when (position) {
                0 -> tab.text = "Menu"
                1 -> tab.text = "Reviews"
            }
        }.attach()


        args.foodTruck.let {
            binding.apply {
                Glide.with(root).load(it.imageUrl).into(foodTruckDetailImage)
                foodTruckDetailPriceLevel.text = "$".repeat(it.priceLevel)
                foodTruckDetailLocation.text = it.location
                foodTruckDetailTime.text = it.formattedTimeInterval
            }

            (requireActivity() as MainActivity).apply {
                title = it.name
            }

        }

        return binding.root
    }
}
