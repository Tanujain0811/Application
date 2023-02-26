package com.example.beersguide.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.beersguide.MainActivity
import com.example.beersguide.activities.MealActivity
import com.example.beersguide.adapter.MealAdapter
import com.example.beersguide.databinding.FragmentHomeBinding
import com.example.beersguide.pojo.Meal
import com.example.beersguide.pojo.MealItem


import com.example.beersguide.viewModel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var randomMeal: Meal
    private lateinit var mealsAdapter: MealAdapter

    companion object{
        const val MEAL_ID = "mealId"
        const val MEAL_NAME = "mealName"
        const val MEAL_THUMB = "mealThumb"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= (activity as MainActivity).viewModel
        mealsAdapter= MealAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        viewModel.getRandomMeal()
        observeRandomMealData()
        onMealImageClick()

        viewModel.getMeals()
        observeMeaklsData()
        onMealListItemClick()

    }

    private fun onMealListItemClick() {
        mealsAdapter.onItemClick={
            val intent=Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,it.idMeal)
            intent.putExtra(MEAL_THUMB,it.strMealThumb)
            intent.putExtra(MEAL_NAME,it.strMeal)
            startActivity(intent)
        }
    }

    private fun setRecyclerView() {
        binding.rvPopularItems.apply {
            layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter=mealsAdapter
        }
    }

    private fun observeMeaklsData() {
        viewModel.mealsLivedata().observe(viewLifecycleOwner
        ) {
            mealsAdapter.setMealList(mealList = it as ArrayList<MealItem>)
        }
     }

    private fun onMealImageClick() {
        binding.ivRandomBeer.setOnClickListener {
            val intent=Intent(activity,MealActivity::class.java)
                intent.putExtra(MEAL_ID,randomMeal.idMeal)
                intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
                intent.putExtra(MEAL_NAME,randomMeal.strMeal)
                startActivity(intent)
        }
    }


    private fun observeRandomMealData() {
     viewModel.observeRandomMealLivedata().observe(viewLifecycleOwner
        ) {
          Glide.with(this@HomeFragment).load(it?.strMealThumb).into(binding.ivRandomBeer)
          this.randomMeal=it
      }
    }
}