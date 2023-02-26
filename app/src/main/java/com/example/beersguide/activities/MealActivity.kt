package com.example.beersguide.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.beersguide.R
import com.example.beersguide.databinding.ActivityMealBinding
import com.example.beersguide.databinding.FragmentHomeBinding
import com.example.beersguide.db.MealDb
import com.example.beersguide.fragments.HomeFragment
import com.example.beersguide.pojo.Meal
import com.example.beersguide.viewModel.HomeViewModel
import com.example.beersguide.viewModel.MealViewModel
import com.example.beersguide.viewModel.MealViewModelFactory

class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var mealViewModel: MealViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mealDb = MealDb.getInstance(this)
        val viewModelFactory = MealViewModelFactory(mealDb)

        mealViewModel=ViewModelProvider(this,viewModelFactory)[MealViewModel::class.java]

        getMealInfo()
        setData()
        mealViewModel.getMealDetail(mealId)
        observeMealLiveData()

        onFavClick()
    }

    private fun onFavClick() {
        binding.btnFav.setOnClickListener {
            favMeal?.let { it1 -> mealViewModel.insertMeal(it1)
            Toast.makeText(this,"Added to Fav",Toast.LENGTH_SHORT).show()}

        }
    }
    private var favMeal:Meal?=null
    private fun observeMealLiveData() {
        mealViewModel.observeMealDetails().observe(this,object :Observer<Meal>{
            override fun onChanged(t: Meal?) {
                favMeal=t
                binding.tvInstructionsDetail.text=t?.strInstructions

            }

        })
    }

    private fun setData() {
        Glide.with(applicationContext).load(mealThumb).into(binding.ivSelectedFood)
         binding.collapsingToolBar.title=mealName
         binding.collapsingToolBar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
         binding.collapsingToolBar.setExpandedTitleColor(resources.getColor(R.color.white))

    }

    private fun getMealInfo() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!


    }
}