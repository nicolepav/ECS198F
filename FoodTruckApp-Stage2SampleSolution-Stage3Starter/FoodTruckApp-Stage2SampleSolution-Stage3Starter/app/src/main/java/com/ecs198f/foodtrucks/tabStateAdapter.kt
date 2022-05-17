package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class tabStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FoodTruckMenuFragment()
        fragment.arguments = Bundle().apply {
            putString(ARG_OBJECT, "empty")
        }
        return fragment
    }

     fun createFragment(position: Int, truck: FoodTruck): Fragment {
        val fragmentMenu = FoodTruckMenuFragment.newInstance(truck)
        val fragmentReview = FoodTruckReviewsFragment.newInstance(truck)

        fragmentMenu.arguments = Bundle().apply {
            putParcelable(ARG_OBJECT, truck)
        }
        fragmentReview.arguments = Bundle().apply {
            putParcelable(ARG_OBJECT, truck)
        }

        when (position) {
            0 -> fragmentMenu
            1 -> fragmentReview
        }

        return fragmentMenu
    }
}
