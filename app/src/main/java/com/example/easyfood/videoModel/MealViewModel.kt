package com.example.easyfood.videoModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.pojo.ListMeal
import com.example.easyfood.pojo.Meal
import com.example.easyfood.retrofit.RetrofitIntance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(): ViewModel() {

    private var mealDetailsLiveData = MutableLiveData<Meal>()

    fun getMealDetail(id: String) {
        RetrofitIntance.api.getMealDetail(id).enqueue(object : Callback<ListMeal> {

            override fun onResponse(call: Call<ListMeal>, response: Response<ListMeal>) {
                if (response.body() != null) {
                    mealDetailsLiveData.value = response.body()!!.meals[0]
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<ListMeal>, t: Throwable) {
                Log.d("MealDetail", t.message.toString())
            }
        })
    }

    fun observeMealDetailLivedata(): LiveData<Meal> {
        return mealDetailsLiveData
    }



}