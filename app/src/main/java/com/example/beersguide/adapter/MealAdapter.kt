package com.example.beersguide.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beersguide.databinding.ItemMealBinding
import com.example.beersguide.pojo.MealItem
import com.example.beersguide.pojo.MealList

class MealAdapter:RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    private var mealList = ArrayList<MealItem>()
    lateinit var onItemClick : ((MealItem)-> Unit)

    fun setMealList(mealList:  ArrayList<MealItem>){
        this.mealList=mealList
        notifyDataSetChanged()

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(ItemMealBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        Glide.with(holder.itemView).load(mealList[position].strMealThumb).into(holder.binding.ivMeal)
        holder.itemView.setOnClickListener {
            onItemClick.invoke(mealList[position])
        }
    }

    override fun getItemCount(): Int {
        return mealList.size
    }
    class MealViewHolder(var binding:ItemMealBinding):RecyclerView.ViewHolder(binding.root)
}