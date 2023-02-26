package com.example.beersguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.beersguide.db.MealDb
import com.example.beersguide.viewModel.HomeViewModel
import com.example.beersguide.viewModel.HomeViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val viewModel:HomeViewModel by lazy {
        val mealDb = MealDb.getInstance(this)
        val homeViewModelFactory=HomeViewModelFactory(mealDb)
        ViewModelProvider(this,homeViewModelFactory)[HomeViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.btm_nav)
        val navController = findNavController(R.id.base_fragment)

        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }
}