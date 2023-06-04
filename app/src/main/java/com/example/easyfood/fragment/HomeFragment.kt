package com.example.easyfood.fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.easyfood.activities.MealActivity
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.pojo.Meal
import com.example.easyfood.viewModel.HomeViewModel


class HomeFragment : Fragment() {

     //tenemos una instancia de nuestra interfaz
     private lateinit var binding: FragmentHomeBinding
     private lateinit var homeMvvm:HomeViewModel

     private lateinit var randomMeal:Meal


     companion object{
         const val MEAL_ID = "id"
         const val MEAL_NAME = "name"
         const val MEAL_THUMB = "th"

     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inicializar mvvm
        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]

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
        //llamo function de la api de random
        homeMvvm.getRandomMeal()
        //observar
        observarRandomMeal()
        onRandomMealClick()

    }

     private fun onRandomMealClick() {
         binding.cardViewId.setOnClickListener {


             val intent = Intent(activity, MealActivity::class.java)
             intent.putExtra(MEAL_ID, randomMeal.idMeal)
             intent.putExtra(MEAL_NAME, randomMeal.strMeal)
             intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
             startActivity(intent)
         }
     }

     //funcion para escuchar los datos
     private fun observarRandomMeal() {
         homeMvvm.observeRandomMealLivedata().observe(viewLifecycleOwner, object :Observer<Meal>{
             override fun onChanged(meal: Meal?) {
                     Glide.with(this@HomeFragment)
                         .load(meal!!.strMealThumb)
                   .into(binding.imgRandomMeal)

                 randomMeal = meal


             }
         })
     }

 }

