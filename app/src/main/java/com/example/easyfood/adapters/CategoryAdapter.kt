package com.example.easyfood.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.databinding.CategoryItemBinding
import com.example.easyfood.databinding.PopularItemBinding
import com.example.easyfood.pojo.Category
import com.example.easyfood.pojo.CategoryMeals

class CategoryAdapter (): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>()  {

    private var categoriesList: List<Category> = ArrayList()

    class CategoryViewHolder(val binding: CategoryItemBinding):
        RecyclerView.ViewHolder(binding.root)

    // constructor
    fun setCategory(categoriesList: List<Category>) {
        this.categoriesList = categoriesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Log.d("TRAGARME", "TRAGARME" + categoriesList)
        holder.binding.apply{
        Glide.with(holder.itemView)
            .load(categoriesList[position].strCategoryThumb)
            .into(holder.binding.imgCategory)

        holder.binding.tvCategoryName.text = categoriesList[position].strCategory
       }
    }
}