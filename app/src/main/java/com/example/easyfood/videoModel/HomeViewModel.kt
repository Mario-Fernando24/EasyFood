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

class HomeViewModel():ViewModel() {

    private var randomMealLiveData = MutableLiveData<Meal>()

    fun getRandomMeal(){

        RetrofitIntance.api.getRandomMeal().enqueue(object : Callback<ListMeal> {

            override fun onResponse(call: Call<ListMeal>, response: Response<ListMeal>) {
                if(response.body() != null){
                    val radomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = radomMeal
                //    Glide.with(this@HomeFragment)
                        //        .load(radomMeal.strMealThumb)
                    //  .into(binding.imgRandomMeal)

                    Log.d("TEST", "MEAL ID ${radomMeal.idMeal} name ${radomMeal.strMeal}")
                }else{
                    return ;
                }
            }

            override fun onFailure(call: Call<ListMeal>, t: Throwable) {
                Log.d("HomeFragment",t.message.toString())
            }
        })
    }

    fun observeRandomMealLivedata():LiveData<Meal>{
        return randomMealLiveData
    }
}