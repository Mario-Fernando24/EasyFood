package com.example.easyfood.retrofit

import com.example.easyfood.pojo.CategoryMeals
import com.example.easyfood.pojo.CategoryList
import com.example.easyfood.pojo.ListMeal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal():Call<ListMeal>

    @GET("lookup.php?i=52864")
    fun getMealDetail(@Query("i") id:String) :Call<ListMeal>

    @GET("filter.php?")
    fun getfilterItemPopular(@Query("a") categoriaName:String) :Call<CategoryList>

    @GET("categories.php")
    fun getAllCategory()

}
//www.themealdb.com/api/json/v1/1/categories.php