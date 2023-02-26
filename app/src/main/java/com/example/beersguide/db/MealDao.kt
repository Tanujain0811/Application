package com.example.beersguide.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.beersguide.pojo.Meal

@Dao
interface MealDao {
      @Insert(onConflict = OnConflictStrategy.REPLACE)
      suspend fun insertMeal(meal: Meal)

      @Delete
      suspend fun deleteMeal(meal: Meal)

      @Query("SELECT * FROM Meal")
     fun getAllMeals():LiveData<List<Meal>>
}