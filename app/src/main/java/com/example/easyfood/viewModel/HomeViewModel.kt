package com.example.easyfood.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.pojo.*
import com.example.easyfood.retrofit.RetrofitIntance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {

    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<CategoryMeals>>()

    fun getRandomMeal(){

        RetrofitIntance.api.getRandomMeal().enqueue(object : Callback<ListMeal> {

            override fun onResponse(call: Call<ListMeal>, response: Response<ListMeal>) {
                if(response.body() != null){
                    val radomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = radomMeal
                }else{

                    return ;
                }
            }

            override fun onFailure(call: Call<ListMeal>, t: Throwable) {
                Log.d("HomeFragment",t.message.toString())
            }
        })
    }

    fun getPopularItems(){

        RetrofitIntance.api.getfilterItemPopular("Canadian").enqueue(object : Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {

                if(response.body() != null){
                    popularItemsLiveData.value =  response.body()!!.meals
                }else{
                    return ;
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("PopularItems",t.message.toString())
            }
        })
    }


    //retornar todas las categorias
    fun getAllCategory(){

        RetrofitIntance.api.getAllCategory().enqueue(object : Callback<MealByCategoryList>{
            override fun onResponse(
                call: Call<MealByCategoryList>,
                response: Response<MealByCategoryList>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<MealByCategoryList>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun observeRandomMealLivedata():LiveData<Meal>{
        return randomMealLiveData
    }

    fun observepopularItemMealLivedata():MutableLiveData<List<CategoryMeals>>{
        return popularItemsLiveData
    }
}