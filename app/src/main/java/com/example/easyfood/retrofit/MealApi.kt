package com.example.easyfood.retrofit

import com.example.easyfood.pojo.ListMeal
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {

    @GET("random.php")
    fun getRandomMeal():Call<ListMeal>


}