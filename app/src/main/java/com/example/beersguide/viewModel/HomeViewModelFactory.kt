package com.example.beersguide.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.beersguide.db.MealDb

class HomeViewModelFactory(private val mealDb: MealDb):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(mealDb) as T
    }
}