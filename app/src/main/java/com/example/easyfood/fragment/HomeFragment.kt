package com.example.easyfood.fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.easyfood.R
import com.example.easyfood.databinding.ActivityMainBinding
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.pojo.ListMeal
import com.example.easyfood.pojo.Meal
import com.example.easyfood.retrofit.RetrofitIntance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


 class HomeFragment : Fragment() {

     //tenemos una instancia de nuestra interfaz
     lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentHomeBinding.inflate( inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RetrofitIntance.api.getRandomMeal().enqueue(object : Callback<ListMeal>{

            override fun onResponse(call: Call<ListMeal>, response: Response<ListMeal>) {
                if(response.body() != null){
                    val radomMeal: Meal = response.body()!!.meals[0]
                    Glide.with(this@HomeFragment)
                        .load(radomMeal.strMealThumb)
                        .into(binding.imgRandomMeal)

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

}

