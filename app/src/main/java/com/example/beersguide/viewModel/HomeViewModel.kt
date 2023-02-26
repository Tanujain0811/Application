package com.example.beersguide.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.beersguide.db.MealDb
import com.example.beersguide.pojo.Meal
import com.example.beersguide.pojo.MealInfo
import com.example.beersguide.pojo.MealItem
import com.example.beersguide.pojo.MealList
import com.example.beersguide.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel(private val mealDb:MealDb):ViewModel() {

    private var randomMealLiveData=MutableLiveData<Meal>()
    private var mealsLiveData=MutableLiveData<List<MealItem>>()
    private var favMealsLiveData=mealDb.mealDao().getAllMeals()
    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object :Callback<MealInfo> {
            override fun onResponse(call: Call<MealInfo>, response: Response<MealInfo>) {
               if(response.body()!=null){
                   var randomBeer:Meal = response.body()!!.meals[0]
                   randomMealLiveData.value=randomBeer

               }
                else{
                    return
               }
            }

            override fun onFailure(call: Call<MealInfo>, t: Throwable) {
                t.message?.let { Log.d("ERROR", it) }
            }

        })
    }

    fun getMeals(){
        RetrofitInstance.api.getMealsInfo("seafood").enqueue(object :Callback<MealList>{
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {

                if(response.body()!=null){
                        mealsLiveData.value = response.body()!!.meals
                }
                else{
                    return
                }

            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                t.message?.let { Log.d("ERROR", it) }
            }

        })
    }
    fun observeRandomMealLivedata():LiveData<Meal>{
        return randomMealLiveData
    }
    fun mealsLivedata():LiveData<List<MealItem>>{
        return mealsLiveData
    }
    fun observeFavMealsLivedata():LiveData<List<Meal>>{
        return favMealsLiveData
    }
}