package com.example.beersguide.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beersguide.databinding.ItemFavBinding
import com.example.beersguide.databinding.ItemMealBinding
import com.example.beersguide.pojo.Meal

class FavMealAdapter:RecyclerView.Adapter<FavMealAdapter.FavMealViewHolder>() {
    class FavMealViewHolder(var binding: ItemFavBinding):RecyclerView.ViewHolder(binding.root)

    private val diffUtil=object:DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }

    }

    val differ=AsyncListDiffer(this,diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMealViewHolder {
            return FavMealViewHolder(ItemFavBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FavMealViewHolder, position: Int) {
        val meal = differ.currentList[position]
        Glide.with(holder.itemView).load(meal.strMealThumb).into(holder.binding.ivMeal)
        holder.binding.tvMealName.text = meal.strMeal
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }
}