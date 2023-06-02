package com.example.easyfood.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.easyfood.R
import com.example.easyfood.databinding.ActivityMealBinding
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.fragment.HomeFragment
import com.example.easyfood.pojo.Meal
import com.example.easyfood.videoModel.HomeViewModel
import com.example.easyfood.videoModel.MealViewModel
import java.sql.RowId

class MealActivity : AppCompatActivity() {


    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private  var mealYoutube=""

    private lateinit var binding: ActivityMealBinding

    //
    private lateinit var mealMvvm: MealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvvm = ViewModelProviders.of(this)[MealViewModel::class.java]
        obtenerInformacionComidasParametro()
        setShowInformation()
        loadingCase()
        mealMvvm.getMealDetail(mealId)
        observeMealDetailLivedata()
        onYutubeInViews()

    }


    private fun obtenerInformacionComidasParametro() {

        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }


    private fun setShowInformation() {


        Glide.with(this@MealActivity)
            .load(mealThumb)
            .into(binding.imgMealDetail)

        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))

    }

    private fun observeMealDetailLivedata() {

        mealMvvm.observeMealDetailLivedata().observe(this, object : Observer<Meal>{
            override fun onChanged(t: Meal?) {


                onResponseCase()
                val meal = t
                binding.tvCategori.text = "Categoria: ${meal!!.strCategory}"
                binding.tvArea.text = "Area: ${meal!!.strArea}"
                binding.tvIntructionDetail.text = "${meal!!.strInstructions}"

                mealYoutube = meal.strYoutube


            }

        })
    }


    private fun loadingCase(){

        binding.progressBar.visibility = View.VISIBLE
        binding.btnAddToFavory.visibility = View.INVISIBLE
        binding.tvIntructionDetail.visibility = View.INVISIBLE
        binding.tvCategori.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.imgYoutube.visibility = View.INVISIBLE

    }

    private fun onResponseCase(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.btnAddToFavory.visibility = View.VISIBLE
        binding.tvIntructionDetail.visibility = View.VISIBLE
        binding.tvCategori.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.imgYoutube.visibility = View.VISIBLE
    }

    private fun onYutubeInViews() {

        if (mealYoutube!="" || mealYoutube!=null){
            binding.imgYoutube.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mealYoutube))
                startActivity(intent)
            }
        }
    }


}