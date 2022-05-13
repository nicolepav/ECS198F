package com.example.project2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodService {

    @GET("/food-trucks")
    fun listFoodTrucks() : Call<List<FoodTruck>>

    // need to convert string to int in FoofTruck class and then here
    @GET("/food-trucks/{id}")
    fun foodTruck(@Path("id") id: String): Call<DetailInfo>

    @GET("/food-trucks/{truck-id}/items")
    fun listFoodItems(@Path("truck-id") id: String): Call<List<FoodItem>>

}