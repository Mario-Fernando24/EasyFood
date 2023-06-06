package com.example.easyfood.adapters
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.databinding.PopularItemBinding
import com.example.easyfood.pojo.CategoryMeals


class MostPopularRecyclerAdapter (): RecyclerView.Adapter<MostPopularRecyclerAdapter.PopularMealViewHolder>() {

    private var mealsList: List<CategoryMeals> = ArrayList()

    //ArrayList<Category>()
    class PopularMealViewHolder(val binding: PopularItemBinding):
        RecyclerView.ViewHolder(binding.root)

    // constructor
    fun setMeals(mealsList: List<CategoryMeals>) {
        this.mealsList = mealsList
       // Log.d("piñajunior", "MARIOIOIOIO" +this.mealsList);
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

     override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {

             Log.d("Dios","Dios"+mealsList)
             Glide.with(holder.itemView)
                 .load(mealsList[position].strMealThumb)
                 .into(holder.binding.imgPopularMealItem)

     }

     override fun getItemCount(): Int {return mealsList.size }

}