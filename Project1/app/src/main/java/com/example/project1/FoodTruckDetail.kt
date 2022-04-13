package com.example.project1

import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.project1.databinding.ActivityFoodTruckDetailBinding


class FoodTruckDetail : AppCompatActivity() {

    private lateinit var binding : ActivityFoodTruckDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =   ActivityFoodTruckDetailBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        val truck = intent.getParcelableExtra<FoodTruck>("truck")

        if (truck != null) {
            binding.name.text = truck.name
            binding.location.text = truck.location
            binding.time.text = truck.hours
            binding.descrip.text = truck.description

            // gets the image from resources folder and sets image view as appropriate
            val myBitmap = BitmapFactory.decodeResource(resources, truck?.image)
            findViewById<ImageView>(R.id.imageView).setImageBitmap(myBitmap)

            binding.link.setOnClickListener {
                val uri = Uri.parse(truck.link)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }

        }


    }
}