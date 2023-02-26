package com.example.beersguide.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beersguide.db.MealDb
import com.example.beersguide.pojo.Meal
import com.example.beersguide.pojo.MealInfo
import com.example.beersguide.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(val mealDb: MealDb):ViewModel() {
    private var mealDetailLiveData=MutableLiveData<Meal>()
    fun getMealDetail(id:String){
        RetrofitInstance.api.getMealDetail(id).enqueue(object :Callback<MealInfo>{
            override fun onResponse(call: Call<MealInfo>, response: Response<MealInfo>) {
                if(response.body()!=null){
                    mealDetailLiveData.value=response.body()!!.meals[0]
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

    fun observeMealDetails():LiveData<Meal>{
        return mealDetailLiveData
    }
    fun insertMeal(meal: Meal){
        viewModelScope.launch {
            mealDb.mealDao().insertMeal(meal)
        }
    }
    fun deleteMeal(meal: Meal){
        viewModelScope.launch {
            mealDb.mealDao().deleteMeal(meal)
        }
    }
}