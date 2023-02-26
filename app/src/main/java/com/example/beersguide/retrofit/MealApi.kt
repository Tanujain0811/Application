package com.example.beersguide.retrofit

import com.example.beersguide.pojo.Meal
import com.example.beersguide.pojo.MealInfo
import com.example.beersguide.pojo.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("random.php")
    fun getRandomMeal():Call<MealInfo>

    @GET("lookup.php?")
    fun getMealDetail(@Query("i") id:String):Call<MealInfo>

    @GET("filter.php?")
    fun getMealsInfo(@Query("c") name:String):Call<MealList>

}